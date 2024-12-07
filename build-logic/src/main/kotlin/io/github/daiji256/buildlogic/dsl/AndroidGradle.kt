package io.github.daiji256.buildlogic.dsl

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.android(action: BaseExtension.() -> Unit) =
    extensions.configure(action)

internal fun Project.androidApplication(action: BaseAppModuleExtension.() -> Unit) =
    extensions.configure(action)

internal fun Project.androidLibrary(action: LibraryExtension.() -> Unit) =
    extensions.configure(action)
