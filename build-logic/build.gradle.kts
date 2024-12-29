plugins {
    `kotlin-dsl`
}

group = "io.github.daiji256.sandbox.buildlogic"

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
}

gradlePlugin {
    plugins {
        register("android.application") {
            id = "buildlogic.android.application"
            implementationClass = "io.github.daiji256.sandbox.buildlogic.AndroidApplicationPlugin"
        }
        register("android.library") {
            id = "buildlogic.android.library"
            implementationClass = "io.github.daiji256.sandbox.buildlogic.AndroidLibraryPlugin"
        }
        register("android.compose") {
            id = "buildlogic.android.compose"
            implementationClass = "io.github.daiji256.sandbox.buildlogic.AndroidComposePlugin"
        }
    }
}
