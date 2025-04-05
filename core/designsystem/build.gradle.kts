plugins {
    alias(libs.plugins.buildlogic.android.library)
    alias(libs.plugins.buildlogic.android.compose)
    alias(libs.plugins.buildlogic.roborazzi)
}

android {
    namespace = "io.github.daiji256.showcase.core.designsystem"
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.activity.compose)
    testImplementation(projects.core.testing)
}
