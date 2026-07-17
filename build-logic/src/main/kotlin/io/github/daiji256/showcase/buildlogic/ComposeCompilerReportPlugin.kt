package io.github.daiji256.showcase.buildlogic

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

@Suppress("unused")
class ComposeCompilerReportPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            tasks.register("generateComposeCompilerReport", ComposeCompilerReportTask::class.java) {
                group = "verification"
                description = "Generates compose compiler report"
                metricsDir.set(rootProject.layout.buildDirectory.dir("compose-metrics"))
                reportFile.set(rootProject.layout.buildDirectory.file("compose-compiler-report.md"))
            }
        }
    }
}

abstract class ComposeCompilerReportTask : DefaultTask() {
    @get:InputDirectory
    abstract val metricsDir: DirectoryProperty

    @get:OutputFile
    abstract val reportFile: RegularFileProperty

    @TaskAction
    fun generate() {
        val metricsFiles = metricsDir.asFileTree.matching { include("**/*.json") }.files
        val moduleMetrics = metricsFiles.associate { file ->
            val moduleName = file.parentFile.parentFile.name
            val metrics = decodeMetrics(file.readText())
            moduleName to metrics
        }
        val report = buildString {
            appendLine("# Compose Compiler Report")
            appendLine()
            appendLine("## Total")
            appendLine()
            appendLine("<details><summary>Expand for details</summary>")
            appendLine()
            appendLine(moduleMetrics.values.sum().toTablesMd())
            appendLine()
            appendLine("</details>")
            appendLine()

            appendLine("## Modules")
            moduleMetrics.toList().sortedBy { (module, _) -> module }.forEach { (module, metrics) ->
                appendLine()
                appendLine("### $module")
                appendLine()
                appendLine("<details><summary>Expand for details</summary>")
                appendLine()
                appendLine(metrics.toTablesMd())
                appendLine()
                appendLine("</details>")
            }
        }

        reportFile.get().asFile.writeText(report)
    }
}

private data class ComposeMetrics(
    val skippableComposables: Int,
    val restartableComposables: Int,
    val readonlyComposables: Int,
    val totalComposables: Int,
    val restartGroups: Int,
    val totalGroups: Int,
    val staticArguments: Int,
    val certainArguments: Int,
    val knownStableArguments: Int,
    val knownUnstableArguments: Int,
    val unknownStableArguments: Int,
    val totalArguments: Int,
    val markedStableClasses: Int,
    val inferredStableClasses: Int,
    val inferredUnstableClasses: Int,
    val inferredUncertainClasses: Int,
    val effectivelyStableClasses: Int,
    val totalClasses: Int,
    val memoizedLambdas: Int,
    val singletonLambdas: Int,
    val singletonComposableLambdas: Int,
    val composableLambdas: Int,
    val totalLambdas: Int,
)

private fun decodeMetrics(json: String): ComposeMetrics {
    val element = Json.parseToJsonElement(json).jsonObject
    fun JsonObject.getInt(key: String) = getValue(key).jsonPrimitive.int
    return ComposeMetrics(
        skippableComposables = element.getInt("skippableComposables"),
        restartableComposables = element.getInt("restartableComposables"),
        readonlyComposables = element.getInt("readonlyComposables"),
        totalComposables = element.getInt("totalComposables"),
        restartGroups = element.getInt("restartGroups"),
        totalGroups = element.getInt("totalGroups"),
        staticArguments = element.getInt("staticArguments"),
        certainArguments = element.getInt("certainArguments"),
        knownStableArguments = element.getInt("knownStableArguments"),
        knownUnstableArguments = element.getInt("knownUnstableArguments"),
        unknownStableArguments = element.getInt("unknownStableArguments"),
        totalArguments = element.getInt("totalArguments"),
        markedStableClasses = element.getInt("markedStableClasses"),
        inferredStableClasses = element.getInt("inferredStableClasses"),
        inferredUnstableClasses = element.getInt("inferredUnstableClasses"),
        inferredUncertainClasses = element.getInt("inferredUncertainClasses"),
        effectivelyStableClasses = element.getInt("effectivelyStableClasses"),
        totalClasses = element.getInt("totalClasses"),
        memoizedLambdas = element.getInt("memoizedLambdas"),
        singletonLambdas = element.getInt("singletonLambdas"),
        singletonComposableLambdas = element.getInt("singletonComposableLambdas"),
        composableLambdas = element.getInt("composableLambdas"),
        totalLambdas = element.getInt("totalLambdas"),
    )
}

private fun Collection<ComposeMetrics>.sum(): ComposeMetrics {
    var skippableComposables = 0
    var restartableComposables = 0
    var readonlyComposables = 0
    var totalComposables = 0
    var restartGroups = 0
    var totalGroups = 0
    var staticArguments = 0
    var certainArguments = 0
    var knownStableArguments = 0
    var knownUnstableArguments = 0
    var unknownStableArguments = 0
    var totalArguments = 0
    var markedStableClasses = 0
    var inferredStableClasses = 0
    var inferredUnstableClasses = 0
    var inferredUncertainClasses = 0
    var effectivelyStableClasses = 0
    var totalClasses = 0
    var memoizedLambdas = 0
    var singletonLambdas = 0
    var singletonComposableLambdas = 0
    var composableLambdas = 0
    var totalLambdas = 0
    this.forEach {
        skippableComposables += it.skippableComposables
        restartableComposables += it.restartableComposables
        readonlyComposables += it.readonlyComposables
        totalComposables += it.totalComposables
        restartGroups += it.restartGroups
        totalGroups += it.totalGroups
        staticArguments += it.staticArguments
        certainArguments += it.certainArguments
        knownStableArguments += it.knownStableArguments
        knownUnstableArguments += it.knownUnstableArguments
        unknownStableArguments += it.unknownStableArguments
        totalArguments += it.totalArguments
        markedStableClasses += it.markedStableClasses
        inferredStableClasses += it.inferredStableClasses
        inferredUnstableClasses += it.inferredUnstableClasses
        inferredUncertainClasses += it.inferredUncertainClasses
        effectivelyStableClasses += it.effectivelyStableClasses
        totalClasses += it.totalClasses
        memoizedLambdas += it.memoizedLambdas
        singletonLambdas += it.singletonLambdas
        singletonComposableLambdas += it.singletonComposableLambdas
        composableLambdas += it.composableLambdas
        totalLambdas += it.totalLambdas
    }
    return ComposeMetrics(
        skippableComposables = skippableComposables,
        restartableComposables = restartableComposables,
        readonlyComposables = readonlyComposables,
        totalComposables = totalComposables,
        restartGroups = restartGroups,
        totalGroups = totalGroups,
        staticArguments = staticArguments,
        certainArguments = certainArguments,
        knownStableArguments = knownStableArguments,
        knownUnstableArguments = knownUnstableArguments,
        unknownStableArguments = unknownStableArguments,
        totalArguments = totalArguments,
        markedStableClasses = markedStableClasses,
        inferredStableClasses = inferredStableClasses,
        inferredUnstableClasses = inferredUnstableClasses,
        inferredUncertainClasses = inferredUncertainClasses,
        effectivelyStableClasses = effectivelyStableClasses,
        totalClasses = totalClasses,
        memoizedLambdas = memoizedLambdas,
        singletonLambdas = singletonLambdas,
        singletonComposableLambdas = singletonComposableLambdas,
        composableLambdas = composableLambdas,
        totalLambdas = totalLambdas,
    )
}

private fun ComposeMetrics.toTablesMd(): String = buildString {
    fun value(value: Int, total: Int) =
        "$value (${if (total == 0) "-" else 100 * value / total}%)"
    appendLine("#### Composables")
    appendLine()
    appendLine("| Type | Value |")
    appendLine("| :--- | ---: |")
    appendLine("| Skippable | ${value(skippableComposables, totalComposables)} |")
    appendLine("| Restartable | ${value(restartableComposables, totalComposables)} |")
    appendLine("| Readonly | ${value(readonlyComposables, totalComposables)} |")
    appendLine("| Total | $totalComposables |")
    appendLine()
    appendLine("#### Groups")
    appendLine()
    appendLine("| Type | Value |")
    appendLine("| :--- | ---: |")
    appendLine("| Restart Groups | ${value(restartGroups, totalGroups)} |")
    appendLine("| Total | $totalGroups |")
    appendLine()
    appendLine("#### Arguments")
    appendLine()
    appendLine("| Type | Value |")
    appendLine("| :--- | ---: |")
    appendLine("| Static | ${value(staticArguments, totalArguments)} |")
    appendLine("| Certain | ${value(certainArguments, totalArguments)} |")
    appendLine("| Known Stable | ${value(knownStableArguments, totalArguments)} |")
    appendLine("| Known Unstable | ${value(knownUnstableArguments, totalArguments)} |")
    appendLine("| Unknown Stable | ${value(unknownStableArguments, totalArguments)} |")
    appendLine("| Total | $totalArguments |")
    appendLine()
    appendLine("#### Classes")
    appendLine()
    appendLine("| Type | Value |")
    appendLine("| :--- | ---: |")
    appendLine("| Marked Stable | ${value(markedStableClasses, totalClasses)} |")
    appendLine("| Inferred Stable | ${value(inferredStableClasses, totalClasses)} |")
    appendLine("| Inferred Unstable | ${value(inferredUnstableClasses, totalClasses)} |")
    appendLine("| Inferred Uncertain | ${value(inferredUncertainClasses, totalClasses)} |")
    appendLine("| Effectively Stable | ${value(effectivelyStableClasses, totalClasses)} |")
    appendLine("| Total | $totalClasses |")
    appendLine()
    appendLine("#### Lambdas")
    appendLine()
    appendLine("| Type | Value |")
    appendLine("| :--- | ---: |")
    appendLine("| Memoized | ${value(memoizedLambdas, totalLambdas)} |")
    appendLine("| Singleton | ${value(singletonLambdas, totalLambdas)} |")
    appendLine("| Singleton Composable | ${value(singletonComposableLambdas, totalLambdas)} |")
    appendLine("| Composable | ${value(composableLambdas, totalLambdas)} |")
    appendLine("| Total | $totalLambdas |")
}
