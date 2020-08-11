package gradle.tasks

import gradle.constants.Constants
import gradle.tasks.reusable.GenericCustomTask
import org.apache.commons.io.FileUtils
import org.gradle.api.tasks.TaskAction

class GenerateApplicationTask extends GenericCustomTask {

    static final String id = "generateApplication"

    GenerateApplicationTask() {
        group = Constants.groupCodeGenerationTasks
        description = "Generates a new application structure into src/applications folder"
    }

    private void ensureApplicationDoesNotExist(String appName) {
        String boundedContextsFolder = String.format("%s%s", project.rootDir.absolutePath, "/src/applications".replace("/", File.separator))
        Iterator<File> children = new File(boundedContextsFolder).listFiles().iterator()
        boolean exists = false
        while (!exists && children.hasNext()) {
            File folder = children.next()
            exists = folder.isDirectory() && folder.name.toLowerCase() == appName.toLowerCase()
        }
        if (exists)
            throw new Exception(String.format("An application with name '%s' already exists.", appName))
    }

    private void createBasePackages(String sourceFolder, String appName, String appFolderPath) {
        String folderToCreate = String.format("%s/%s/java", appFolderPath, sourceFolder).replace("/", File.separator)
        getBasePackageParts().collect { folder ->
            folderToCreate = String.format("%s/%s", folderToCreate, folder).replace("/", File.separator)
            new File(folderToCreate).mkdir()
        }
        folderToCreate = String.format("%s/%s", folderToCreate, appName).replace("/", File.separator)
        new File(folderToCreate).mkdir()
    }

    @TaskAction
    void action() {
        String appName = getProperty("appName").replace(" ", "_")
        ensureApplicationDoesNotExist(appName)

        String sourceFolder = String.format("%s%s", project.rootDir.absolutePath, "/buildSrc/src/main/resources/code_templates/applications/newApplication").replace("/", File.separator)
        String targetFolder = String.format("%s%s%s", project.rootDir.absolutePath, "/src/applications/", appName).replace("/", File.separator)
        FileUtils.copyDirectory(new File(sourceFolder), new File(targetFolder))

        createBasePackages("main", appName, targetFolder)
        createBasePackages("test", appName, targetFolder)
    }
}