package gradle.tasks

import gradle.constants.Constants
import gradle.tasks.reusable.GenerateModuleUtil
import gradle.tasks.reusable.GenericCustomTask
import org.gradle.api.tasks.TaskAction

class GenerateModuleTask extends GenericCustomTask {

    static final String id = "generateModuleIntoBoundedContext"

    GenerateModuleTask() {
        group = Constants.groupCodeGenerationTasks
        description = "Generates a new module structure into a specified bounded context"
    }

    @TaskAction
    void action() {
        String boundedContextName = getProperty("boundedContextName").replace(" ", "_")
        String moduleName = getProperty("moduleName").replace(" ", "_")
        GenerateModuleUtil generateModuleUtil = new GenerateModuleUtil(project)
        generateModuleUtil.createModule(boundedContextName, moduleName)
    }
}