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

                val baseProperty = providers.gradleProperty("base").orNull
                    ?: error("-Pbase is required")
                val headProperty = providers.gradleProperty("head").orNull
                    ?: error("-Phead is required")
                val outputProperty = providers.gradleProperty("output").orNull
                    ?: error("-Poutput is required")

                baseDir.set(rootProject.layout.projectDirectory.dir(baseProperty))
                headDir.set(rootProject.layout.projectDirectory.dir(headProperty))
                outputFile.set(rootProject.layout.projectDirectory.file(outputProperty))
            }
        }
    }
}

abstract class ComposeStabilityCheckTask : DefaultTask() {
    @get:InputDirectory
    abstract val baseDir: DirectoryProperty

    @get:InputDirectory
    abstract val headDir: DirectoryProperty

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @TaskAction
    fun generate() {
        val baseDirFile = baseDir.get().asFile
        val headDirFile = headDir.get().asFile

        val baseMetricsDir = File(baseDirFile, "metrics")
        val baseReportsDir = File(baseDirFile, "reports")
        val headMetricsDir = File(headDirFile, "metrics")
        val headReportsDir = File(headDirFile, "reports")

        val baseModuleMetrics =
            baseMetricsDir
                .walkBottomUp()
                .filter { it.extension == "json" }
                .associate { it.parentFile.parentFile.name to decodeMetrics(it.readText()) }
        val baseModuleReports =
            baseReportsDir
                .walkBottomUp()
                .filter { it.extension == "txt" }
                .groupBy { it.parentFile.name }
        val moduleMetrics =
            headMetricsDir
                .walkBottomUp()
                .filter { it.extension == "json" }
                .associate { it.parentFile.parentFile.name to decodeMetrics(it.readText()) }
        val moduleReports =
            headReportsDir
                .walkBottomUp()
                .filter { it.extension == "txt" }
                .groupBy { it.parentFile.name }

        val report = buildString {
            appendLine("# Compose Stability Check Report")
            appendLine()
            appendLine("## Total")
            appendLine()
            appendLine("<details><summary>Expand for details</summary>")
            appendLine()
            appendLine(
                tables(
                    base = baseModuleMetrics.values.takeIf { it.isNotEmpty() }?.sum(),
                    head = moduleMetrics.values.sum(),
                ),
            )
            appendLine()
            appendLine("</details>")
            appendLine()

            appendLine("## Modules")
            (moduleMetrics.keys + baseModuleMetrics.keys).sorted().forEach { module ->
                appendLine()
                appendLine("### $module")
                appendLine()
                appendLine("<details><summary>Expand for details</summary>")
                appendLine()
                appendLine(
                    tables(
                        base = baseModuleMetrics[module],
                        head = moduleMetrics[module],
                    ),
                )
                appendLine()
                val reports = moduleReports[module]
                val baseReports = baseModuleReports[module]
                reports?.sortedBy { it.name }?.forEach { reportFile ->
                    val baseReportFile = baseReports?.find { it.name == reportFile.name }
                    val diff = generateDiff(base = baseReportFile, head = reportFile)
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

        outputFile.get().asFile.writeText(report)
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

private fun tables(base: ComposeMetrics?, head: ComposeMetrics?): String = buildString {
    appendLine("#### Composables")
    appendLine()
    appendLine("| Type | Value | Diff |")
    appendLine("| :--- | ---: | ---: |")
    appendLine(
        tableRow(
            type = "Skippable",
            base = base?.skippableComposables,
            head = head?.skippableComposables,
        ),
    )
    appendLine(
        tableRow(
            type = "Restartable",
            base = base?.restartableComposables,
            head = head?.restartableComposables,
        ),
    )
    appendLine(
        tableRow(
            type = "Readonly",
            base = base?.readonlyComposables,
            head = head?.readonlyComposables,
        ),
    )
    appendLine(
        tableRow(
            type = "Total",
            base = base?.totalComposables,
            head = head?.totalComposables,
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
            base = base?.restartGroups,
            head = head?.restartGroups,
        ),
    )
    appendLine(
        tableRow(
            type = "Total",
            base = base?.totalGroups,
            head = head?.totalGroups,
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
            base = base?.staticArguments,
            head = head?.staticArguments,
        ),
    )
    appendLine(
        tableRow(
            type = "Certain",
            base = base?.certainArguments,
            head = head?.certainArguments,
        ),
    )
    appendLine(
        tableRow(
            type = "Known Stable",
            base = base?.knownStableArguments,
            head = head?.knownStableArguments,
        ),
    )
    appendLine(
        tableRow(
            type = "Known Unstable",
            base = base?.knownUnstableArguments,
            head = head?.knownUnstableArguments,
        ),
    )
    appendLine(
        tableRow(
            type = "Unknown Stable",
            base = base?.unknownStableArguments,
            head = head?.unknownStableArguments,
        ),
    )
    appendLine(
        tableRow(
            type = "Total",
            base = base?.totalArguments,
            head = head?.totalArguments,
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
            base = base?.markedStableClasses,
            head = head?.markedStableClasses,
        ),
    )
    appendLine(
        tableRow(
            type = "Inferred Stable",
            base = base?.inferredStableClasses,
            head = head?.inferredStableClasses,
        ),
    )
    appendLine(
        tableRow(
            type = "Inferred Unstable",
            base = base?.inferredUnstableClasses,
            head = head?.inferredUnstableClasses,
        ),
    )
    appendLine(
        tableRow(
            type = "Inferred Uncertain",
            base = base?.inferredUncertainClasses,
            head = head?.inferredUncertainClasses,
        ),
    )
    appendLine(
        tableRow(
            type = "Effectively Stable",
            base = base?.effectivelyStableClasses,
            head = head?.effectivelyStableClasses,
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
            base = base?.totalLambdas,
            head = head?.totalLambdas,
        ),
    )
    appendLine(
        tableRow(
            type = "Memoized",
            base = base?.memoizedLambdas,
            head = head?.memoizedLambdas,
        ),
    )
    appendLine(
        tableRow(
            type = "Singleton",
            base = base?.singletonLambdas,
            head = head?.singletonLambdas,
        ),
    )
    appendLine(
        tableRow(
            type = "Singleton Composable",
            base = base?.singletonComposableLambdas,
            head = head?.singletonComposableLambdas,
        ),
    )
    appendLine(
        tableRow(
            type = "Composable",
            base = base?.composableLambdas,
            head = head?.composableLambdas,
        ),
    )
    appendLine(
        tableRow(
            type = "Total",
            base = base?.totalLambdas,
            head = head?.totalLambdas,
        ),
    )
}

private fun tableRow(type: String, base: Int?, head: Int?): String =
    "| $type | ${head ?: "-"} | ${(head ?: 0) - (base ?: 0)} |"

private fun generateDiff(base: File?, head: File?): String? {
    val baseLines = base?.readLines().orEmpty()
    val headLines = head?.readLines().orEmpty()
    val patch = DiffUtils.diff(baseLines, headLines)
    if (patch.deltas.isEmpty()) return null
    return UnifiedDiffUtils.generateUnifiedDiff(
        base?.name,
        head?.name,
        baseLines,
        patch,
        3,
    ).joinToString("\n")
}
