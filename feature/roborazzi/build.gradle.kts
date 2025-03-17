plugins {
    alias(libs.plugins.buildlogic.android.library)
    alias(libs.plugins.buildlogic.android.compose)
}

android {
    namespace = "io.github.daiji256.showcase.feature.roborazzi"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.ui)
    testImplementation(projects.core.testing)
}
