package io.github.daiji256.showcase.buildlogic.util

import com.android.build.api.dsl.CompileSdkSpec

internal fun CompileSdkSpec.configureCompileSdk(compileSdkVersion: String) {
    val parts = compileSdkVersion.split(".")
    val major = parts[0].toInt()
    val minor = parts.getOrNull(1)?.toInt()
    version = release(major) {
        if (minor != null) {
            minorApiLevel = minor
        }
    }
}
