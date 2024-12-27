plugins {
    alias(libs.plugins.buildlogic.android.library)
}

android {
    namespace = "io.github.daiji256.core.common"
}

dependencies {
    implementation(libs.core.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.testParameterInjector)
}
