package gradle.tasks

import gradle.constants.Constants
import gradle.tasks.reusable.GenerateModuleUtil
import gradle.tasks.reusable.GenericCustomTask
import org.gradle.api.tasks.TaskAction

class GenerateBoundedContextTask extends GenericCustomTask {

    static final String id = "generateBoundedContext"

    GenerateBoundedContextTask() {
        group = Constants.groupCodeGenerationTasks
        description = "Generates a new bounded context structure"
    }

    private void ensureBoundedContextDoesNotExist(String boundedContextName) {
        String boundedContextsFolder = String.format("%s%s", project.rootDir.absolutePath, "/src/bounded_contexts".replace("/", File.separator))
        Iterator<File> children = new File(boundedContextsFolder).listFiles().iterator()
        boolean exists = false
        while (!exists && children.hasNext()) {
            File folder = children.next()
            exists = folder.isDirectory() && folder.name.toLowerCase() == boundedContextName.toLowerCase()
        }
        if (exists)
            throw new Exception(String.format("A bounded context with name '%s' already exists.", boundedContextName))
    }

    @TaskAction
    void action() {
        String boundedContextName = getProperty("boundedContextName").replace(" ", "_")
        ensureBoundedContextDoesNotExist(boundedContextName)

        String boundedContextFolder = String.format("%s%s%s", project.rootDir.absolutePath, "/src/bounded_contexts/", boundedContextName).replace("/", File.separator)
        new File(boundedContextFolder).mkdir()

        GenerateModuleUtil generateModuleUtil = new GenerateModuleUtil(project)
        generateModuleUtil.createModule(boundedContextName, "shared")
    }
}