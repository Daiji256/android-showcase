package io.github.daiji256.showcase.buildlogic

import io.github.daiji256.showcase.buildlogic.dsl.implementation
import io.github.daiji256.showcase.buildlogic.dsl.ksp
import io.github.daiji256.showcase.buildlogic.dsl.library
import io.github.daiji256.showcase.buildlogic.dsl.libs
import io.github.daiji256.showcase.buildlogic.dsl.plugin
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
                implementation(libs.library("hilt.android"))
                implementation(libs.library("hilt.core"))
                ksp(libs.library("hilt-compiler"))
            }
        }
    }
}
