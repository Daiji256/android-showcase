package io.github.daiji256.showcase.buildlogic

import io.github.daiji256.showcase.buildlogic.dsl.androidLibrary
import io.github.daiji256.showcase.buildlogic.dsl.configureKotlinAndroid
import io.github.daiji256.showcase.buildlogic.dsl.implementation
import io.github.daiji256.showcase.buildlogic.dsl.library
import io.github.daiji256.showcase.buildlogic.dsl.libs
import io.github.daiji256.showcase.buildlogic.dsl.plugin
import io.github.daiji256.showcase.buildlogic.dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugin("androidLibrary").pluginId)
                apply(libs.plugin("kotlinAndroid").pluginId)
                apply(libs.plugin("kotlinSerialization").pluginId)
            }

            androidLibrary {
                configureKotlinAndroid(this)
            }

            dependencies {
                testImplementation(libs.library("kotlin.test"))
                testImplementation(libs.library("kotlinx.coroutines.test"))
            }
        }
    }
}
