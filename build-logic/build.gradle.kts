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
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.compose.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(libs.roborazzi.gradlePlugin)
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
        register("roborazzi") {
            id = "buildlogic.roborazzi"
            implementationClass = "io.github.daiji256.showcase.buildlogic.RoborazziPlugin"
        }
    }
}
