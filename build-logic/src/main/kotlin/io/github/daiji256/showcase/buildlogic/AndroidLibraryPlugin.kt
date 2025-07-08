package io.github.daiji256.showcase.buildlogic

import io.github.daiji256.showcase.buildlogic.dsl.androidLibrary
import io.github.daiji256.showcase.buildlogic.dsl.configureKotlinAndroid
import io.github.daiji256.showcase.buildlogic.dsl.libs
import io.github.daiji256.showcase.buildlogic.dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project

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
        }
    }
}
