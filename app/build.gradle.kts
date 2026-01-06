plugins {
    alias(libs.plugins.buildlogic.android.application)
    alias(libs.plugins.buildlogic.compose)
    alias(libs.plugins.buildlogic.hilt)
    alias(libs.plugins.aboutlibraries)
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

    lint {
        sarifReport = true
        checkDependencies = true
    }
}

aboutLibraries {
    license {
        strictMode = com.mikepenz.aboutlibraries.plugin.StrictMode.FAIL
        allowedLicenses = setOf("Apache-2.0")
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(projects.feature.customtabs)
    implementation(projects.feature.hiltcomposable)
    implementation(projects.feature.ktlint)
    implementation(projects.feature.license)
    implementation(projects.feature.localsnackbarhoststate)
    implementation(projects.feature.navigation2arguments)
    implementation(projects.feature.roborazzi)
    implementation(projects.feature.safeurihandler)
    implementation(projects.feature.systemstyle)
    implementation(libs.activity.compose)
    testImplementation(projects.core.testing)
}
