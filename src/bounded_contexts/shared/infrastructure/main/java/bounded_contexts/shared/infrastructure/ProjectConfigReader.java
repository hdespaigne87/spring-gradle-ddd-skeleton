package bounded_contexts.shared.infrastructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ProjectConfigReader {

    public static String getBasePackage() {
        Properties props = readProperties();
        return props.getProperty("basePackage");
    }

    private static Properties readProperties() {
        Properties projectProperties = new Properties();
        try {
            projectProperties.load(new FileInputStream(new File("").getAbsolutePath() + File.separator + "gradle.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return projectProperties;
    }
}
