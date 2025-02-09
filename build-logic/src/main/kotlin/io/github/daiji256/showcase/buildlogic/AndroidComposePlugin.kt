package io.github.daiji256.showcase.buildlogic

import io.github.daiji256.showcase.buildlogic.dsl.android
import io.github.daiji256.showcase.buildlogic.dsl.debugImplementation
import io.github.daiji256.showcase.buildlogic.dsl.implementation
import io.github.daiji256.showcase.buildlogic.dsl.ktlintRuleset
import io.github.daiji256.showcase.buildlogic.dsl.library
import io.github.daiji256.showcase.buildlogic.dsl.libs
import io.github.daiji256.showcase.buildlogic.dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugin("composeCompiler").pluginId)
                apply(libs.plugin("ktlintGradle").pluginId)
            }

            android {
                buildFeatures.compose = true
            }

            dependencies {
                implementation(platform(libs.library("compose.bom")))
                implementation(libs.library("compose.ui"))
                implementation(libs.library("compose.ui.tooling.preview"))
                implementation(libs.library("compose.material3"))
                implementation(libs.library("navigation.compose"))
                debugImplementation(libs.library("compose.ui.tooling"))
                ktlintRuleset(libs.library("composeRules"))
            }
        }
    }
}
