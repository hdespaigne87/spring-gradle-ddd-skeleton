package starter;

import org.reflections.Reflections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Set;

public class Starter {

    private static final String BASE_PACKAGE_TO_SCAN = ProjectConfigReader.getBasePackage();

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("There are not enough arguments");
        }

        String  applicationName = args[0];
        ensureApplicationExist(applicationName);
        Class<?> applicationClass = applications().get(applicationName);

        SpringApplication app = new SpringApplication(applicationClass);
        app.run(args);
    }

    private static String extractApplicationName(Class<?> applicationClass) {
        String[] parts = applicationClass.getName().split("\\.");
        return parts[parts.length - 2];
    }

    private static HashMap<String, Class<?>> applications() {
        HashMap<String, Class<?>> applications = new HashMap<>();
        Reflections reflections = new Reflections(BASE_PACKAGE_TO_SCAN);
        Set<Class<?>> applicationClasses = reflections.getTypesAnnotatedWith(SpringBootApplication.class);
        for(Class<?> _class : applicationClasses) {
            applications.put(extractApplicationName(_class), _class);
        }
        return applications;
    }

    private static void ensureApplicationExist(String applicationName) {
        if (!applications().containsKey(applicationName)) {
            throw new RuntimeException(String.format(
                    "The application <%s> doesn't exist. Valids:\n- %s",
                    applicationName,
                    String.join("\n- ", applications().keySet())
            ));
        }
    }
}