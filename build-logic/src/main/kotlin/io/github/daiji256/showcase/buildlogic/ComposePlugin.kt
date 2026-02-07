package io.github.daiji256.showcase.buildlogic

import com.android.build.api.dsl.CommonExtension
import io.github.daiji256.showcase.buildlogic.util.library
import io.github.daiji256.showcase.buildlogic.util.libs
import io.github.daiji256.showcase.buildlogic.util.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

@Suppress("unused")
class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugin("kotlin.compose").pluginId)
            }

            extensions.configure<CommonExtension> {
                buildFeatures.compose = true
            }

            extensions.configure<KotlinAndroidProjectExtension> {
                compilerOptions {
                    optIn.add("androidx.compose.material3.ExperimentalMaterial3Api")
                }
            }

            dependencies {
                "implementation"(platform(libs.library("compose.bom")))
                "implementation"(libs.library("compose.ui"))
                "implementation"(libs.library("compose.ui.tooling.preview"))
                "implementation"(libs.library("compose.material3"))
                "implementation"(libs.library("hilt.lifecycle.viewmodel.compose"))
                "implementation"(libs.library("lifecycle.viewmodel.navigation3"))
                "implementation"(libs.library("navigation3.runtime"))
                "implementation"(libs.library("navigation3.ui"))
                "debugImplementation"(libs.library("compose.ui.tooling"))
            }
        }
    }
}
