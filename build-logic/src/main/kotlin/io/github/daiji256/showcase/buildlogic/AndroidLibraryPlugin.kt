package io.github.daiji256.showcase.buildlogic

import com.android.build.api.dsl.LibraryExtension
import io.github.daiji256.showcase.buildlogic.util.getAllWarningsAsErrors
import io.github.daiji256.showcase.buildlogic.util.libs
import io.github.daiji256.showcase.buildlogic.util.plugin
import io.github.daiji256.showcase.buildlogic.util.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

@Suppress("unused")
class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugin("android.library").pluginId)
                apply(libs.plugin("kotlin.serialization").pluginId)
            }

            extensions.configure<KotlinAndroidProjectExtension> {
                jvmToolchain(libs.version("jdk").toInt())
                compilerOptions {
                    allWarningsAsErrors = getAllWarningsAsErrors()
                    optIn.addAll(
                        "kotlin.uuid.ExperimentalUuidApi",
                        "kotlinx.coroutines.ExperimentalCoroutinesApi",
                    )
                }
            }

            extensions.configure<LibraryExtension> {
                compileSdk {
                    version = release(libs.version("compileSdk").toInt())
                }
                defaultConfig {
                    minSdk {
                        version = release(libs.version("minSdk").toInt())
                    }
                }
            }
        }
    }
}
