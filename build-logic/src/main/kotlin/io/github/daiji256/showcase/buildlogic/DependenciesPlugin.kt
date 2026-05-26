package io.github.daiji256.showcase.buildlogic

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.component.ModuleComponentIdentifier
import org.gradle.api.artifacts.result.ResolvedArtifactResult
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.maven.MavenModule
import org.gradle.maven.MavenPomArtifact
import org.w3c.dom.Element
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.collections.set

@Suppress("unused")
class DependenciesPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val pomFilesProvider = target.providers.provider {
            collectResolvedDependencyPomFiles(target)
        }
        val generateDependencies =
            target.tasks.register("generateDependencies", GenerateDependenciesTask::class.java) {
                pomFiles.from(pomFilesProvider)
                dependencyOverridesFile.set(
                    target.layout.projectDirectory
                        .file("dependencies-config/dependency-overrides.json"),
                )
                dependenciesOutputFile.set(
                    target.layout.projectDirectory
                        .file("src/main/res/raw/dependencies.json"),
                )
            }
        target.tasks.matching { it.name == "preBuild" }.configureEach {
            dependsOn(generateDependencies)
        }
    }
}

abstract class GenerateDependenciesTask : DefaultTask() {
    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    abstract val pomFiles: ConfigurableFileCollection

    @get:InputFile
    @get:Optional
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val dependencyOverridesFile: RegularFileProperty

    @get:OutputFile
    abstract val dependenciesOutputFile: RegularFileProperty

    init {
        group = "documentation"
        description = "Generates dependencies metadata JSON"
    }

    @TaskAction
    fun generate() {
        val pomDependencies = readPomDependencies(pomFiles)
        val dependencyOverrides = readOverrides(dependencyOverridesFile)
        val dependencies = (pomDependencies.keys + dependencyOverrides.keys)
            .map { Dependency(override = dependencyOverrides[it], default = pomDependencies[it]) }
            .sortedBy { it.id }
            .distinctBy { it.copy(id = "dummy") }

        val jsonObject = mapOf("dependencies" to dependencies.map { it.toJsonMap() })
        val json = JsonOutput.prettyPrint(JsonOutput.toJson(jsonObject)) + "\n"

        val outputFile = dependenciesOutputFile.get().asFile
        outputFile.parentFile.mkdirs()
        outputFile.writeText(json)
        logger.lifecycle("Wrote ${dependencies.size} dependencies to ${outputFile.path}")
    }
}

private fun collectResolvedDependencyPomFiles(project: Project): List<File> {
    val moduleIds = project.configurations
        .filter { cfg ->
            cfg.isCanBeResolved && cfg.name == "releaseRuntimeClasspath"
        }
        .flatMap { cfg ->
            cfg.incoming.resolutionResult.allComponents
                .mapNotNull { component -> component.id as? ModuleComponentIdentifier }
        }
    val pomResults = project.dependencies
        .createArtifactResolutionQuery()
        .forComponents(moduleIds)
        .withArtifacts(MavenModule::class.java, MavenPomArtifact::class.java)
        .execute()
    return pomResults.resolvedComponents.map { resolvedComponent ->
        resolvedComponent
            .getArtifacts(MavenPomArtifact::class.java)
            .filterIsInstance<ResolvedArtifactResult>()
            .single()
            .file
    }
}

private fun parsePom(pomFile: File): NullableDependency {
    val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
    val root = documentBuilder.parse(pomFile).documentElement
    val groupId = root.text("groupId")
        ?: root.children("parent").firstOrNull()?.text("groupId")
        ?: error("missing groupId: ${pomFile.absolutePath}")
    val artifactId = root.text("artifactId")
        ?: error("missing artifactId: ${pomFile.absolutePath}")
    val licenses = root.children("licenses").flatMap { it.children("license") }
    check(licenses.size <= 1) {
        "$groupId:$artifactId: multiple licenses"
    }
    return NullableDependency(
        id = "$groupId:$artifactId",
        name = root.text("name"),
        uri = root.text("url"),
        author = root.children("organization").firstOrNull()?.text("name")
            ?: root.children("developers").firstOrNull()
                ?.children("developer")
                ?.mapNotNull { it.text("name") }
                ?.joinToString(separator = ", "),
        licenseName = licenses.firstOrNull()?.text("name"),
        licenseUri = licenses.firstOrNull()?.text("url"),
    )
}

private fun Element.text(tagName: String): String? =
    children(tagName).firstOrNull()?.textContent?.ifEmpty { null }

private fun Element.children(tagName: String): List<Element> =
    buildList {
        for (index in 0 until childNodes.length) {
            val node = childNodes.item(index)
            if (node is Element && node.tagName == tagName) {
                add(node)
            }
        }
    }

private fun readPomDependencies(
    pomFiles: ConfigurableFileCollection,
): Map<String, NullableDependency> =
    buildMap {
        pomFiles.files.forEach { pomFile ->
            val pomDependency = parsePom(pomFile)
            check(pomDependency.id !in this) {
                "duplicate id ${pomDependency.id}: ${pomFile.absolutePath}"
            }
            set(pomDependency.id, pomDependency)
        }
    }

private fun readOverrides(
    dependencyOverridesFile: RegularFileProperty,
): Map<String, NullableDependency> {
    if (!dependencyOverridesFile.isPresent) return emptyMap()
    val overrideFile = dependencyOverridesFile.get().asFile
    if (!overrideFile.exists()) return emptyMap()

    @Suppress("UNCHECKED_CAST")
    val overrides = JsonSlurper().parse(overrideFile) as Map<String, Map<String, Any?>>
    return overrides.mapValues { (id, data) ->
        NullableDependency(
            id = id,
            name = data.getString("name"),
            uri = data.getString("uri"),
            author = data.getString("author"),
            licenseName = data.getString("license_name"),
            licenseUri = data.getString("license_uri"),
        )
    }
}

private fun Map<String, Any?>.getString(key: String): String? =
    (this[key] as String?)?.ifEmpty { null }

private data class NullableDependency(
    val id: String,
    val name: String?,
    val uri: String?,
    val author: String?,
    val licenseName: String?,
    val licenseUri: String?,
)

private data class Dependency(
    val id: String,
    val name: String,
    val uri: String,
    val author: String,
    val licenseName: String,
    val licenseUri: String,
) {
    constructor(override: NullableDependency?, default: NullableDependency?) : this(
        id = override?.id ?: default?.id
            ?: error("missing id"),
        name = override?.name ?: default?.name
            ?: error("${override?.id ?: default?.id ?: "unknown"}: missing name"),
        uri = override?.uri ?: default?.uri
            ?: error("${override?.id ?: default?.id ?: "unknown"}: missing uri"),
        author = override?.author ?: default?.author
            ?: error("${override?.id ?: default?.id ?: "unknown"}: missing author"),
        licenseName = override?.licenseName ?: default?.licenseName
            ?: error("${override?.id ?: default?.id ?: "unknown"}: missing license name"),
        licenseUri = override?.licenseUri ?: default?.licenseUri
            ?: error("${override?.id ?: default?.id ?: "unknown"}: missing license uri"),
    )

    fun toJsonMap(): Map<String, Any> = mapOf<String, Any>(
        "id" to id,
        "name" to name,
        "uri" to uri,
        "author" to author,
        "license_name" to licenseName,
        "license_uri" to licenseUri,
    )
}
