package io.github.daiji256.showcase.buildlogic.dsl

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.implementation(artifact: Dependency) =
    add("implementation", artifact)

internal fun DependencyHandlerScope.implementation(artifact: MinimalExternalModuleDependency) =
    add("implementation", artifact)

internal fun DependencyHandlerScope.debugImplementation(artifact: MinimalExternalModuleDependency) =
    add("debugImplementation", artifact)

internal fun DependencyHandlerScope.testImplementation(artifact: MinimalExternalModuleDependency) =
    add("testImplementation", artifact)

internal fun DependencyHandlerScope.ksp(artifact: MinimalExternalModuleDependency) =
    add("ksp", artifact)
