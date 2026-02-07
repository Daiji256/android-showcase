package io.github.daiji256.showcase.buildlogic.util

import org.gradle.api.Project

fun Project.getAllWarningsAsErrors(): Boolean =
    providers.gradleProperty("allWarningsAsErrors")
        .map { it.toBoolean() }.orElse(false).get()
