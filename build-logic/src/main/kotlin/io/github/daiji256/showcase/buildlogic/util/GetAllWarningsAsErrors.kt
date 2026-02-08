package io.github.daiji256.showcase.buildlogic.util

import org.gradle.api.Project
import org.gradle.api.provider.Provider

internal fun Project.getAllWarningsAsErrors(): Provider<Boolean> =
    providers.gradleProperty("allWarningsAsErrors")
        .map { it.toBoolean() }.orElse(false)
