plugins {
    alias(libs.plugins.buildlogic.android.library)
    alias(libs.plugins.buildlogic.android.compose)
}

android {
    namespace = "io.github.daiji256.showcase.core.ui"
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.markdown)
}
