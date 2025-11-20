plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.roborazzi) apply false
    alias(libs.plugins.spotless) apply false
}

allprojects {
    apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)
    extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint(libs.versions.ktlint.get())
                .customRuleSets(listOf(libs.composeRules.get().toString()))
        }
        kotlinGradle {
            target("**/*.kts")
            targetExclude("**/build/**/*.kts")
            ktlint(libs.versions.ktlint.get())
        }
    }
}
