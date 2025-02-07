plugins {
    alias(libs.plugins.buildlogic.android.library)
    alias(libs.plugins.buildlogic.android.compose)
}

android {
    namespace = "io.github.daiji256.showcase.core.designsystem"
}

dependencies {
    implementation(libs.activity.compose)
}
