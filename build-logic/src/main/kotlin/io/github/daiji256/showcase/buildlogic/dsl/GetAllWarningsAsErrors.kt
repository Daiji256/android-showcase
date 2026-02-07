package io.github.daiji256.showcase.buildlogic.dsl

import org.gradle.api.Project

fun Project.getAllWarningsAsErrors(): Boolean =
    providers.gradleProperty("allWarningsAsErrors")
        .map { it.toBoolean() }.orElse(false).get()
