plugins {
    alias(libs.plugins.buildlogic.android.library)
    alias(libs.plugins.buildlogic.compose)
}

android {
    namespace = "io.github.daiji256.showcase.core.testing"
}

dependencies {
    api(libs.kotlin.test.junit)
    api(libs.kotlinx.coroutines.test)
    api(libs.robolectric)
    api(libs.turbine)
    implementation(libs.androidx.test.rules)
    implementation(libs.compose.ui.test.junit4)
    implementation(libs.preview.scanner)
    implementation(libs.roborazzi)
    implementation(libs.roborazzi.compose.preview.scanner.support)
}
