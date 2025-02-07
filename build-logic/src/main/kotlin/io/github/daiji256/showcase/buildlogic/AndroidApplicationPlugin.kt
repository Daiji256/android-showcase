package io.github.daiji256.showcase.buildlogic

import io.github.daiji256.showcase.buildlogic.dsl.androidApplication
import io.github.daiji256.showcase.buildlogic.dsl.configureKotlinAndroid
import io.github.daiji256.showcase.buildlogic.dsl.libs
import io.github.daiji256.showcase.buildlogic.dsl.plugin
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
