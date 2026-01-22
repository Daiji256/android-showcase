package io.github.daiji256.showcase.buildlogic.dsl

import org.gradle.api.artifacts.Dependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.implementation(artifact: Dependency) =
    add("implementation", artifact)

internal fun DependencyHandlerScope.debugImplementation(artifact: Dependency) =
    add("debugImplementation", artifact)

internal fun DependencyHandlerScope.testImplementation(artifact: Dependency) =
    add("testImplementation", artifact)

internal fun DependencyHandlerScope.ksp(artifact: Dependency) =
    add("ksp", artifact)
