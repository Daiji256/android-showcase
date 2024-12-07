plugins {
    alias(libs.plugins.buildlogic.android.library)
    alias(libs.plugins.buildlogic.android.compose)
}

android {
    namespace = "io.github.daiji256.core.designsystem"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
}
