plugins {
    alias(libs.plugins.buildlogic.android.library)
}

android {
    namespace = "io.github.daiji256.feature.ktlint"
}

dependencies {
    implementation(libs.core.ktx)
}
