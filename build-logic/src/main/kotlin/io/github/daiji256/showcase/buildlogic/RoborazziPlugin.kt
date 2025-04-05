package io.github.daiji256.showcase.buildlogic

import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import io.github.daiji256.showcase.buildlogic.dsl.android
import io.github.daiji256.showcase.buildlogic.dsl.library
import io.github.daiji256.showcase.buildlogic.dsl.libs
import io.github.daiji256.showcase.buildlogic.dsl.plugin
import io.github.daiji256.showcase.buildlogic.dsl.roborazzi
import io.github.daiji256.showcase.buildlogic.dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
@OptIn(ExperimentalRoborazziApi::class)
class RoborazziPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugin("roborazzi").pluginId)
            }

            android {
                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                        all {
                            it.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware"
                        }
                    }
                }
            }

            roborazzi {
                generateComposePreviewRobolectricTests {
                    enable = true
                    packages = provider { listOf(android.namespace) }
                    robolectricConfig = mapOf(
                        "sdk" to "[35]",
                        "qualifiers" to "RobolectricDeviceQualifiers.MediumPhone",
                    )
                    includePrivatePreviews = true
                    testerQualifiedClassName =
                        "io.github.daiji256.showcase.core.testing.PreviewTester"
                }
            }

            dependencies {
                testImplementation(libs.library("composable.preview.scanner.android"))
                testImplementation(libs.library("junit"))
                testImplementation(libs.library("robolectric"))
                testImplementation(libs.library("roborazzi"))
                testImplementation(libs.library("roborazzi.compose"))
                testImplementation(libs.library("roborazzi.compose.preview.scanner.support"))
                testImplementation(libs.library("roborazzi.core"))
            }
        }
    }
}
