package io.github.daiji256.showcase.buildlogic

import io.github.daiji256.showcase.buildlogic.util.library
import io.github.daiji256.showcase.buildlogic.util.libs
import io.github.daiji256.showcase.buildlogic.util.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class HiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugin("hilt").pluginId)
                apply(libs.plugin("ksp").pluginId)
            }

            dependencies {
                "implementation"(libs.library("hilt.android"))
                "ksp"(libs.library("hilt.compiler"))
            }
        }
    }
}
