plugins {
    alias(libs.plugins.buildlogic.android.library)
}

android {
    namespace = "io.github.daiji256.core.designsystem"
}

dependencies {
    implementation(libs.core.ktx)
}
