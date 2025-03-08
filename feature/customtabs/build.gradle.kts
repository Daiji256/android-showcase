plugins {
    alias(libs.plugins.buildlogic.android.library)
    alias(libs.plugins.buildlogic.android.compose)
    alias(libs.plugins.buildlogic.android.hilt)
}

android {
    namespace = "io.github.daiji256.showcase.feature.customtabs"
}

dependencies {
    implementation(projects.core.ui)
    implementation(libs.activity.compose)
    implementation(libs.browser)
}
