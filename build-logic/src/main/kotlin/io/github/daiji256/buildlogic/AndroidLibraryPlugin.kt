package io.github.daiji256.buildlogic

import io.github.daiji256.buildlogic.dsl.androidLibrary
import io.github.daiji256.buildlogic.dsl.configureKotlinAndroid
import io.github.daiji256.buildlogic.dsl.libs
import io.github.daiji256.buildlogic.dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project

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
        }
    }
}
