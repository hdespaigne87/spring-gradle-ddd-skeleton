package gradle.tasks.reusable

import org.apache.commons.io.FileUtils
import org.gradle.api.Project

class GenerateModuleUtil {

    Project project

    GenerateModuleUtil(Project project) {
        this.project = project
    }

    private void ensureBoundedContextExist(String boundedContextName) {
        String boundedContextsFolder = String.format("%s%s", project.rootDir.absolutePath, "/src/bounded_contexts".replace("/", File.separator))
        Iterator<File> children = new File(boundedContextsFolder).listFiles().iterator()
        boolean exists = false
        while (!exists && children.hasNext()) {
            File folder = children.next()
            exists = folder.isDirectory() && folder.name.toLowerCase() == boundedContextName.toLowerCase()
        }
        if (!exists)
            throw new Exception(String.format("Not found a bounded context with name '%s'.", boundedContextName))
    }

    private void ensureModuleDoesNotExist(String boundedContextName, String moduleName) {
        String modulesFolder = String.format("%s%s%s", project.rootDir.absolutePath, "/src/bounded_contexts/".replace("/", File.separator), boundedContextName)
        Iterator<File> children = new File(modulesFolder).listFiles().iterator()
        boolean exists = false
        while (!exists && children.hasNext()) {
            File folder = children.next()
            exists = folder.isDirectory() && folder.name.toLowerCase() == moduleName.toLowerCase()
        }
        if (exists)
            throw new Exception(String.format("A module with name '%s' already exists into bounded context '%s'.", moduleName, boundedContextName))
    }

    private String createBasePackages(String sourceFolder, String boundedContextName, String moduleName, String moduleFolderPath, String layerName) {
        String folderToCreate = String.format("%s/%s/%s/java", moduleFolderPath, layerName, sourceFolder).replace("/", File.separator)
        project.property("basePackage").split("\\.").collect { folder ->
            folderToCreate = String.format("%s/%s", folderToCreate, folder).replace("/", File.separator)
            new File(folderToCreate).mkdir()
        }
        folderToCreate = String.format("%s/%s", folderToCreate, boundedContextName).replace("/", File.separator)
        new File(folderToCreate).mkdir()
        folderToCreate = String.format("%s/%s", folderToCreate, moduleName).replace("/", File.separator)
        new File(folderToCreate).mkdir()
        folderToCreate = String.format("%s/%s", folderToCreate, layerName).replace("/", File.separator)
        new File(folderToCreate).mkdir()
        return folderToCreate
    }

    private void createApplicationBasePackages(String applicationBasePackage) {
        String folderToCreate = String.format("%s/%s", applicationBasePackage, "events_handlers").replace("/", File.separator)
        new File(folderToCreate).mkdir()
        folderToCreate = String.format("%s/%s", applicationBasePackage, "use_cases").replace("/", File.separator)
        new File(folderToCreate).mkdir()
    }

    private void createDomainBasePackages(String domainBasePackage) {
        String folderToCreate = String.format("%s/%s", domainBasePackage, "aggregate_roots").replace("/", File.separator)
        new File(folderToCreate).mkdir()
        folderToCreate = String.format("%s/%s", domainBasePackage, "entities").replace("/", File.separator)
        new File(folderToCreate).mkdir()
        folderToCreate = String.format("%s/%s", domainBasePackage, "repositories").replace("/", File.separator)
        new File(folderToCreate).mkdir()
        folderToCreate = String.format("%s/%s", domainBasePackage, "services").replace("/", File.separator)
        new File(folderToCreate).mkdir()
        folderToCreate = String.format("%s/%s", domainBasePackage, "value_objects").replace("/", File.separator)
        new File(folderToCreate).mkdir()
    }

    private void createBasePackages(String sourceFolder, String boundedContextName, String moduleName, String moduleFolderPath) {
        String applicationBasePackage = createBasePackages(sourceFolder, boundedContextName, moduleName, moduleFolderPath, "application")
        String domainBasePackage = createBasePackages(sourceFolder, boundedContextName, moduleName, moduleFolderPath, "domain")
        createBasePackages(sourceFolder, boundedContextName, moduleName, moduleFolderPath, "infrastructure")

        createApplicationBasePackages(applicationBasePackage)
        createDomainBasePackages(domainBasePackage)
    }

    void createModule(String boundedContextName, String moduleName) {
        ensureBoundedContextExist(boundedContextName)
        ensureModuleDoesNotExist(boundedContextName, moduleName)

        String sourceFolder = String.format("%s%s", project.rootDir.absolutePath, "/buildSrc/src/main/resources/code_templates/modules/newModule").replace("/", File.separator)
        String targetFolder = String.format("%s%s%s/%s", project.rootDir.absolutePath, "/src/bounded_contexts/", boundedContextName, moduleName).replace("/", File.separator)
        FileUtils.copyDirectory(new File(sourceFolder), new File(targetFolder))

        createBasePackages("main", boundedContextName, moduleName, targetFolder)
        createBasePackages("test", boundedContextName, moduleName, targetFolder)
    }
}