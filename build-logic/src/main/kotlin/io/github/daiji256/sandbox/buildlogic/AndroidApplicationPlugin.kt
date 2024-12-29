package io.github.daiji256.sandbox.buildlogic

import io.github.daiji256.sandbox.buildlogic.dsl.androidApplication
import io.github.daiji256.sandbox.buildlogic.dsl.configureKotlinAndroid
import io.github.daiji256.sandbox.buildlogic.dsl.libs
import io.github.daiji256.sandbox.buildlogic.dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugin("androidApplication").pluginId)
                apply(libs.plugin("kotlinAndroid").pluginId)
            }

            androidApplication {
                configureKotlinAndroid(this)
            }
        }
    }
}
