plugins {
    alias(libs.plugins.buildlogic.android.library)
}

android {
    namespace = "io.github.daiji256.sandbox.core.common"
}

dependencies {
    testImplementation(libs.testParameterInjector)
}
