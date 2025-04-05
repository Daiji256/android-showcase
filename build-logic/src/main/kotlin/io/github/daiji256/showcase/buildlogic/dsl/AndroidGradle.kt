package io.github.daiji256.showcase.buildlogic.dsl

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

internal fun Project.android(action: BaseExtension.() -> Unit) =
    extensions.configure(action)

internal val Project.android: BaseExtension
    get() = extensions.getByType<BaseExtension>()

internal fun Project.androidApplication(action: BaseAppModuleExtension.() -> Unit) =
    extensions.configure(action)

internal fun Project.androidLibrary(action: LibraryExtension.() -> Unit) =
    extensions.configure(action)
