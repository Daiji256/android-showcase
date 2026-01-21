package io.github.daiji256.showcase.buildlogic

import com.android.build.api.dsl.CommonExtension
import io.github.daiji256.showcase.buildlogic.dsl.library
import io.github.daiji256.showcase.buildlogic.dsl.libs
import io.github.daiji256.showcase.buildlogic.dsl.plugin
import io.github.daiji256.showcase.buildlogic.dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class RobolectricPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugin("roborazzi").pluginId)
            }

            extensions.configure<CommonExtension> {
                testOptions.unitTests {
                    isIncludeAndroidResources = true
                }
            }

            dependencies {
                testImplementation(libs.library("junit"))
                testImplementation(libs.library("robolectric"))
            }
        }
    }
}
