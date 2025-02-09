// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.ktlintGradle) apply false
    alias(libs.plugins.kotlinSerialization) apply false
}

allprojects {
    afterEvaluate {
        apply(plugin = libs.plugins.ktlintGradle.get().pluginId)
        configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
            version.set("1.5.0")
            reporters {
                reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
            }
            filter {
                include("**.kt")
                include("**.kts")
                exclude("**/build/**")
            }
        }
    }
}

true // Needed to make the Suppress annotation work for the plugins block
