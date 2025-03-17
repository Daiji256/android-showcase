plugins {
    alias(libs.plugins.buildlogic.android.application)
    alias(libs.plugins.buildlogic.android.compose)
    alias(libs.plugins.buildlogic.android.hilt)
}

android {
    namespace = "io.github.daiji256.showcase"

    defaultConfig {
        applicationId = "io.github.daiji256.showcase"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(projects.feature.customtabs)
    implementation(projects.feature.ktlint)
    implementation(projects.feature.roborazzi)
    implementation(libs.activity.compose)
    testImplementation(projects.core.testing)
}
