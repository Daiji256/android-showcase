package io.github.daiji256.showcase.buildlogic.dsl

import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal fun Project.configureKotlin() {
    extensions.configure<KotlinAndroidProjectExtension> {
        jvmToolchain(libs.version("jdk").toInt())
        compilerOptions {
            allWarningsAsErrors = true
            freeCompilerArgs.add("-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")
        }
    }
}
