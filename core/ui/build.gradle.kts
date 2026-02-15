plugins {
    alias(libs.plugins.buildlogic.android.library)
    alias(libs.plugins.buildlogic.compose)
    alias(libs.plugins.buildlogic.roborazzi)
}

android {
    namespace = "io.github.daiji256.showcase.core.ui"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(libs.markdown)
    testImplementation(projects.core.testing)
    testImplementation(libs.kotlinx.serialization.json)
}
