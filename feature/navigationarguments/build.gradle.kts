plugins {
    alias(libs.plugins.buildlogic.android.library)
    alias(libs.plugins.buildlogic.compose)
    alias(libs.plugins.buildlogic.hilt)
    alias(libs.plugins.buildlogic.robolectric)
    alias(libs.plugins.buildlogic.roborazzi)
}

android {
    namespace = "io.github.daiji256.showcase.feature.navigationarguments"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(libs.navigation2)
    testImplementation(projects.core.testing)
}
