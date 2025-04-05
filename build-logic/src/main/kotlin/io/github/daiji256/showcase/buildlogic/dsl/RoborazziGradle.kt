package io.github.daiji256.showcase.buildlogic.dsl

import io.github.takahirom.roborazzi.RoborazziExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.roborazzi(action: RoborazziExtension.() -> Unit) =
    extensions.configure<RoborazziExtension>(action)
