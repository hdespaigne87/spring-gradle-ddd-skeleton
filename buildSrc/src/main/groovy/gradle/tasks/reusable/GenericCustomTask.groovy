package gradle.tasks.reusable

import org.gradle.api.DefaultTask

abstract class GenericCustomTask extends DefaultTask {

    @Override
    String getProperty(String propertyKey) {
        return getProperty(propertyKey, true).get()
    }

    String[] getBasePackageParts() {
        return project.property("basePackage").split("\\.")
    }

    Optional<String> getProperty(String propertyKey, boolean required) {
        String value = project.properties.get(propertyKey)
        if (required && value == null)
            throw new Exception(String.format("No value has been specified for property '%s'.", propertyKey))
        return Optional.of(value)
    }
}
