package io.github.daiji256.buildlogic

import io.github.daiji256.buildlogic.dsl.androidLibrary
import io.github.daiji256.buildlogic.dsl.configureKotlinAndroid
import io.github.daiji256.buildlogic.dsl.implementation
import io.github.daiji256.buildlogic.dsl.library
import io.github.daiji256.buildlogic.dsl.libs
import io.github.daiji256.buildlogic.dsl.plugin
import io.github.daiji256.buildlogic.dsl.testImplementation
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
            }

            androidLibrary {
                configureKotlinAndroid(this)
            }

            dependencies {
                implementation(libs.library("kotlinx.coroutines.core"))
                implementation(libs.library("kotlinx.collections.immutable"))
                testImplementation(libs.library("kotlin.test"))
                testImplementation(libs.library("kotlinx.coroutines.test"))
            }
        }
    }
}
