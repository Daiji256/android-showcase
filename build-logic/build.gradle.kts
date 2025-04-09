plugins {
    `kotlin-dsl`
}

group = "io.github.daiji256.showcase.buildlogic"

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.roborazzi.gradlePlugin)
    implementation(libs.roborazzi.core)
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
        register("android.compose") {
            id = "buildlogic.android.compose"
            implementationClass = "io.github.daiji256.showcase.buildlogic.AndroidComposePlugin"
        }
        register("hilt") {
            id = "buildlogic.hilt"
            implementationClass = "io.github.daiji256.showcase.buildlogic.HiltPlugin"
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
