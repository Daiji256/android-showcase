package io.github.daiji256.showcase.buildlogic.dsl

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.ksp(artifact: MinimalExternalModuleDependency) =
    add("ksp", artifact)
