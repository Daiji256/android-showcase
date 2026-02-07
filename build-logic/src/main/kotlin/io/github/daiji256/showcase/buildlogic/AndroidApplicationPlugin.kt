package io.github.daiji256.showcase.buildlogic

import com.android.build.api.dsl.ApplicationExtension
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
class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugin("android.application").pluginId)
            }

            extensions.configure<KotlinAndroidProjectExtension> {
                jvmToolchain(libs.version("jdk").toInt())
                compilerOptions {
                    allWarningsAsErrors = getAllWarningsAsErrors()
                }
            }

            extensions.configure<ApplicationExtension> {
                compileSdk {
                    version = release(libs.version("compileSdk").toInt())
                }
                defaultConfig {
                    minSdk {
                        version = release(libs.version("minSdk").toInt())
                    }
                    targetSdk {
                        version = release(libs.version("targetSdk").toInt())
                    }
                }
            }
        }
    }
}
