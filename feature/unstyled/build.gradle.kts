plugins {
    alias(libs.plugins.buildlogic.android.library)
    alias(libs.plugins.buildlogic.android.compose)
    alias(libs.plugins.buildlogic.roborazzi)
}

android {
    namespace = "io.github.daiji256.showcase.feature.unstyled"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    testImplementation(projects.core.testing)
}
