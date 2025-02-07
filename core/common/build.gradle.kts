plugins {
    alias(libs.plugins.buildlogic.android.library)
}

android {
    namespace = "io.github.daiji256.showcase.core.common"
}

dependencies {
    testImplementation(libs.testParameterInjector)
}
