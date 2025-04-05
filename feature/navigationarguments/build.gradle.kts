plugins {
    alias(libs.plugins.buildlogic.android.library)
    alias(libs.plugins.buildlogic.android.compose)
    alias(libs.plugins.buildlogic.roborazzi)
}

android {
    namespace = "io.github.daiji256.showcase.feature.navigationarguments"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.ui)
    testImplementation(projects.core.testing)
}
