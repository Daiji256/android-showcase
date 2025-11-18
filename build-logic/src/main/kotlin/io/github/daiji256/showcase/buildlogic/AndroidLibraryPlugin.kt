package io.github.daiji256.showcase.buildlogic

import com.android.build.gradle.LibraryExtension
import io.github.daiji256.showcase.buildlogic.dsl.configureKotlin
import io.github.daiji256.showcase.buildlogic.dsl.libs
import io.github.daiji256.showcase.buildlogic.dsl.plugin
import io.github.daiji256.showcase.buildlogic.dsl.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

@Suppress("unused")
class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugin("androidLibrary").pluginId)
                apply(libs.plugin("kotlinAndroid").pluginId)
                apply(libs.plugin("kotlinSerialization").pluginId)
            }

            configureKotlin()

            extensions.configure<LibraryExtension> {
                compileSdk {
                    version = release(libs.version("compileSdk").toInt())
                }
                defaultConfig.minSdk {
                    version = release(libs.version("minSdk").toInt())
                }
            }
        }
    }
}
