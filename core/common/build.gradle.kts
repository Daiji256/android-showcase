plugins {
    alias(libs.plugins.buildlogic.android.library)
}

android {
    namespace = "io.github.daiji256.showcase.core.common"
}

dependencies {
    api(libs.kotlin.logging)
    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.collections.immutable)
    api(libs.kotlinx.serialization.core)
    api(libs.slf4j.simple)
    testImplementation(projects.core.testing)
    testImplementation(libs.testParameterInjector)
}
