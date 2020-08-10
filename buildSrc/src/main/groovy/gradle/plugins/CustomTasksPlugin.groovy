package gradle.plugins

import gradle.tasks.GenerateApplicationTask
import gradle.tasks.GenerateBoundedContextTask
import gradle.tasks.GenerateModuleTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class CustomTasksPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.tasks.create(GenerateModuleTask.id, GenerateModuleTask.class);
        project.tasks.create(GenerateBoundedContextTask.id, GenerateBoundedContextTask.class);
        project.tasks.create(GenerateApplicationTask.id, GenerateApplicationTask.class);
    }
}
