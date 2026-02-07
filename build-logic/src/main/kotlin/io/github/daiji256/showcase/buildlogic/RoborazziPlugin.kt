package io.github.daiji256.showcase.buildlogic

import com.android.build.api.dsl.CommonExtension
import io.github.daiji256.showcase.buildlogic.util.library
import io.github.daiji256.showcase.buildlogic.util.libs
import io.github.daiji256.showcase.buildlogic.util.plugin
import io.github.daiji256.showcase.buildlogic.util.testImplementation
import io.github.takahirom.roborazzi.RoborazziExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

@Suppress("unused")
class RoborazziPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(RobolectricPlugin::class)
                apply(libs.plugin("roborazzi").pluginId)
            }

            extensions.configure<CommonExtension> {
                testOptions.unitTests {
                    all {
                        it.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware"
                    }
                }
            }

            extensions.configure<RoborazziExtension> {
                generateComposePreviewRobolectricTests {
                    enable = true
                    packages = provider {
                        listOf(extensions.getByType<CommonExtension>().namespace!!)
                    }
                    robolectricConfig = mapOf(
                        "qualifiers" to "RobolectricDeviceQualifiers.MediumPhone",
                    )
                    includePrivatePreviews = true
                    useScanOptionParametersInTester = true
                    testerQualifiedClassName =
                        "io.github.daiji256.showcase.core.testing.PreviewTester"
                }
            }

            dependencies {
                testImplementation(platform(libs.library("compose.bom")))
                testImplementation(libs.library("compose.ui.test.junit4"))
                testImplementation(libs.library("preview.scanner"))
                testImplementation(libs.library("roborazzi"))
                testImplementation(libs.library("roborazzi.compose.preview.scanner.support"))
            }
        }
    }
}
