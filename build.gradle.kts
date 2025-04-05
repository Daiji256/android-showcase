plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.ktlintGradle) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.roborazzi) apply false
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
