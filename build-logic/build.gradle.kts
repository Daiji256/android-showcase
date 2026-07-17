plugins {
    `kotlin-dsl`
    alias(libs.plugins.kotlin.serialization)
}

group = "io.github.daiji256.showcase.buildlogic"

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvmToolchain(libs.versions.jdk.get().toInt())
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.roborazzi.gradlePlugin)
    implementation(libs.kotlinx.serialization.json)
}

gradlePlugin {
    plugins {
        register("android.application") {
            id = "buildlogic.android.application"
            implementationClass = "io.github.daiji256.showcase.buildlogic.AndroidApplicationPlugin"
        }
        register("android.library") {
            id = "buildlogic.android.library"
            implementationClass = "io.github.daiji256.showcase.buildlogic.AndroidLibraryPlugin"
        }
        register("compose") {
            id = "buildlogic.compose"
            implementationClass = "io.github.daiji256.showcase.buildlogic.ComposePlugin"
        }
        register("compose.compiler.report") {
            id = "buildlogic.compose.compiler.report"
            implementationClass =
                "io.github.daiji256.showcase.buildlogic.ComposeCompilerReportPlugin"
        }
        register("dependencies") {
            id = "buildlogic.dependencies"
            implementationClass = "io.github.daiji256.showcase.buildlogic.DependenciesPlugin"
        }
        register("robolectric") {
            id = "buildlogic.robolectric"
            implementationClass = "io.github.daiji256.showcase.buildlogic.RobolectricPlugin"
        }
        register("roborazzi") {
            id = "buildlogic.roborazzi"
            implementationClass = "io.github.daiji256.showcase.buildlogic.RoborazziPlugin"
        }
    }
}
