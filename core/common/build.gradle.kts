plugins {
    alias(libs.plugins.buildlogic.android.library)
}

android {
    namespace = "io.github.daiji256.core.common"
}

dependencies {
    implementation(libs.core.ktx)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.testParameterInjector)
}
