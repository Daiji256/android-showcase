package io.github.daiji256.showcase.buildlogic

import com.github.difflib.DiffUtils
import com.github.difflib.UnifiedDiffUtils
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
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

@Suppress("unused")
class ComposeStabilityCheckPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            tasks.register("generateComposeStabilityCheck", ComposeStabilityCheckTask::class.java) {
                group = "verification"
                description = "Generates compose stability check report"
                metricsDir.set(rootProject.layout.buildDirectory.dir("compose-compiler/metrics"))
                reportsDir.set(rootProject.layout.buildDirectory.dir("compose-compiler/reports"))
                val baseMetricsDirProperty = project.findProperty("baseMetricsDir") as? String
                val baseReportsDirProperty = project.findProperty("baseReportsDir") as? String
                if (baseMetricsDirProperty != null) {
                    baseMetricsDir.set(
                        rootProject.layout.projectDirectory.dir(baseMetricsDirProperty),
                    )
                }
                if (baseReportsDirProperty != null) {
                    baseReportsDir.set(
                        rootProject.layout.projectDirectory.dir(baseReportsDirProperty),
                    )
                }
                reportFile.set(rootProject.layout.buildDirectory.file("compose-stability-check.md"))
            }
        }
    }
}

abstract class ComposeStabilityCheckTask : DefaultTask() {
    @get:InputDirectory
    abstract val metricsDir: DirectoryProperty

    @get:InputDirectory
    abstract val reportsDir: DirectoryProperty

    @get:InputDirectory
    @get:Optional
    abstract val baseMetricsDir: DirectoryProperty

    @get:InputDirectory
    @get:Optional
    abstract val baseReportsDir: DirectoryProperty

    @get:OutputFile
    abstract val reportFile: RegularFileProperty

    @TaskAction
    fun generate() {
        val moduleMetrics = metricsDir
            .asFileTree
            .matching { include("**/*.json") }
            .files
            .associate { it.parentFile.parentFile.name to decodeMetrics(it.readText()) }
        val moduleReports = reportsDir
            .asFileTree
            .matching { include("**/*.txt") }
            .files
            .groupBy { it.parentFile.name }
        val baseModuleMetrics = if (baseMetricsDir.isPresent) {
            baseMetricsDir
                .asFileTree
                .matching { include("**/*.json") }
                .files
                .associate { it.parentFile.parentFile.name to decodeMetrics(it.readText()) }
        } else {
            null
        }
        val baseModuleReports = if (baseReportsDir.isPresent) {
            baseReportsDir
                .asFileTree
                .matching { include("**/*.txt") }
                .files
                .groupBy { it.parentFile.name }
        } else {
            null
        }

        val report = buildString {
            appendLine("# Compose Stability Check Report")
            appendLine()
            appendLine("## Total")
            appendLine()
            appendLine("<details><summary>Expand for details</summary>")
            appendLine()
            appendLine(
                tables(
                    metrics = moduleMetrics.values.sum(),
                    baseMetrics = baseModuleMetrics?.values?.sum(),
                ),
            )
            appendLine()
            appendLine("</details>")
            appendLine()

            appendLine("## Modules")
            (moduleMetrics.keys + (baseModuleMetrics?.keys.orEmpty())).sorted().forEach { module ->
                appendLine()
                appendLine("### $module")
                appendLine()
                appendLine("<details><summary>Expand for details</summary>")
                appendLine()
                appendLine(
                    tables(
                        metrics = moduleMetrics[module],
                        baseMetrics = baseModuleMetrics?.get(module),
                    ),
                )
                appendLine()
                val reports = moduleReports[module]
                val baseReports = baseModuleReports?.get(module)
                reports?.sortedBy { it.name }?.forEach { reportFile ->
                    val baseReportFile = baseReports?.find { it.name == reportFile.name }
                    val diff = generateDiff(baseFile = baseReportFile, headFile = reportFile)
                    if (diff != null) {
                        appendLine("#### Diff: ${reportFile.name}")
                        appendLine()
                        appendLine("```diff")
                        appendLine(diff)
                        appendLine("```")
                        appendLine()
                    }
                }

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

private fun tables(metrics: ComposeMetrics?, baseMetrics: ComposeMetrics?): String = buildString {
    appendLine("#### Composables")
    appendLine()
    appendLine("| Type | Value | Diff |")
    appendLine("| :--- | ---: | ---: |")
    appendLine(
        tableRow(
            type = "Skippable",
            value = metrics?.skippableComposables,
            baseValue = baseMetrics?.skippableComposables,
        ),
    )
    appendLine(
        tableRow(
            type = "Restartable",
            value = metrics?.restartableComposables,
            baseValue = baseMetrics?.restartableComposables,
        ),
    )
    appendLine(
        tableRow(
            type = "Readonly",
            value = metrics?.readonlyComposables,
            baseValue = baseMetrics?.readonlyComposables,
        ),
    )
    appendLine(
        tableRow(
            type = "Total",
            value = metrics?.totalComposables,
            baseValue = baseMetrics?.totalComposables,
        ),
    )
    appendLine()
    appendLine("#### Groups")
    appendLine()
    appendLine("| Type | Value | Diff |")
    appendLine("| :--- | ---: | ---: |")
    appendLine(
        tableRow(
            type = "Restart",
            value = metrics?.restartGroups,
            baseValue = baseMetrics?.restartGroups,
        ),
    )
    appendLine(
        tableRow(
            type = "Total",
            value = metrics?.totalGroups,
            baseValue = baseMetrics?.totalGroups,
        ),
    )
    appendLine()
    appendLine("#### Arguments")
    appendLine()
    appendLine("| Type | Value | Diff |")
    appendLine("| :--- | ---: | ---: |")
    appendLine(
        tableRow(
            type = "Static",
            value = metrics?.staticArguments,
            baseValue = baseMetrics?.staticArguments,
        ),
    )
    appendLine(
        tableRow(
            type = "Certain",
            value = metrics?.certainArguments,
            baseValue = baseMetrics?.certainArguments,
        ),
    )
    appendLine(
        tableRow(
            type = "Known Stable",
            value = metrics?.knownStableArguments,
            baseValue = baseMetrics?.knownStableArguments,
        ),
    )
    appendLine(
        tableRow(
            type = "Known Unstable",
            value = metrics?.knownUnstableArguments,
            baseValue = baseMetrics?.knownUnstableArguments,
        ),
    )
    appendLine(
        tableRow(
            type = "Unknown Stable",
            value = metrics?.unknownStableArguments,
            baseValue = baseMetrics?.unknownStableArguments,
        ),
    )
    appendLine(
        tableRow(
            type = "Total",
            value = metrics?.totalArguments,
            baseValue = baseMetrics?.totalArguments,
        ),
    )
    appendLine()
    appendLine("#### Classes")
    appendLine()
    appendLine("| Type | Value | Diff |")
    appendLine("| :--- | ---: | ---: |")
    appendLine(
        tableRow(
            type = "Marked Stable",
            value = metrics?.markedStableClasses,
            baseValue = baseMetrics?.markedStableClasses,
        ),
    )
    appendLine(
        tableRow(
            type = "Inferred Stable",
            value = metrics?.inferredStableClasses,
            baseValue = baseMetrics?.inferredStableClasses,
        ),
    )
    appendLine(
        tableRow(
            type = "Inferred Unstable",
            value = metrics?.inferredUnstableClasses,
            baseValue = baseMetrics?.inferredUnstableClasses,
        ),
    )
    appendLine(
        tableRow(
            type = "Inferred Uncertain",
            value = metrics?.inferredUncertainClasses,
            baseValue = baseMetrics?.inferredUncertainClasses,
        ),
    )
    appendLine(
        tableRow(
            type = "Effectively Stable",
            value = metrics?.effectivelyStableClasses,
            baseValue = baseMetrics?.effectivelyStableClasses,
        ),
    )
    appendLine()
    appendLine("#### Lambdas")
    appendLine()
    appendLine("| Type | Value | Diff |")
    appendLine("| :--- | ---: | ---: |")
    appendLine(
        tableRow(
            type = "Total",
            value = metrics?.totalLambdas,
            baseValue = baseMetrics?.totalLambdas,
        ),
    )
    appendLine(
        tableRow(
            type = "Memoized",
            value = metrics?.memoizedLambdas,
            baseValue = baseMetrics?.memoizedLambdas,
        ),
    )
    appendLine(
        tableRow(
            type = "Singleton",
            value = metrics?.singletonLambdas,
            baseValue = baseMetrics?.singletonLambdas,
        ),
    )
    appendLine(
        tableRow(
            type = "Singleton Composable",
            value = metrics?.singletonComposableLambdas,
            baseValue = baseMetrics?.singletonComposableLambdas,
        ),
    )
    appendLine(
        tableRow(
            type = "Composable",
            value = metrics?.composableLambdas,
            baseValue = baseMetrics?.composableLambdas,
        ),
    )
    appendLine(
        tableRow(
            type = "Total",
            value = metrics?.totalLambdas,
            baseValue = baseMetrics?.totalLambdas,
        ),
    )
}

private fun tableRow(type: String, value: Int?, baseValue: Int?): String =
    "| $type | ${value ?: "-"} | ${(value ?: 0) - (baseValue ?: 0)} |"

private fun generateDiff(baseFile: File?, headFile: File?): String? {
    val headLines = headFile?.readLines().orEmpty()
    val baseLines = baseFile?.readLines().orEmpty()
    val patch = DiffUtils.diff(baseLines, headLines)
    if (patch.deltas.isEmpty()) return null
    return UnifiedDiffUtils.generateUnifiedDiff(
        baseFile?.name,
        headFile?.name,
        baseLines,
        patch,
        3,
    ).joinToString("\n")
}
