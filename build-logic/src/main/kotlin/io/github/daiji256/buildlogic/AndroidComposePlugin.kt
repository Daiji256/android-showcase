package io.github.daiji256.buildlogic

import io.github.daiji256.buildlogic.dsl.android
import io.github.daiji256.buildlogic.dsl.debugImplementation
import io.github.daiji256.buildlogic.dsl.implementation
import io.github.daiji256.buildlogic.dsl.ktlintRuleset
import io.github.daiji256.buildlogic.dsl.library
import io.github.daiji256.buildlogic.dsl.libs
import io.github.daiji256.buildlogic.dsl.plugin
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
                implementation(platform(libs.library("compose-bom")))
                implementation(libs.library("ui"))
                implementation(libs.library("ui-tooling-preview"))
                implementation(libs.library("material3"))
                debugImplementation(libs.library("ui-tooling"))
                ktlintRuleset(libs.library("composeRules"))
            }
        }
    }
}
