plugins {
    alias(libs.plugins.buildlogic.android.application)
    alias(libs.plugins.buildlogic.compose)
    alias(libs.plugins.buildlogic.hilt)
    alias(libs.plugins.aboutlibraries)
}

android {
    namespace = "io.github.daiji256.showcase"

    ndkVersion = libs.versions.ndk.get()

    defaultConfig {
        applicationId = "io.github.daiji256.showcase"
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("signing/debug.keystore")
            storePassword = "showcase"
            keyAlias = "debug"
            keyPassword = "showcase"
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }

        create("staging") {
            matchingFallbacks.add("release")
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".staging"
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }

        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            ndk.debugSymbolLevel = "FULL"
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
    implementation(projects.feature.navnode)
    implementation(projects.feature.pastel)
    implementation(projects.feature.roborazzi)
    implementation(projects.feature.safeurihandler)
    implementation(projects.feature.systemstyle)
    implementation(libs.activity.compose)
    testImplementation(projects.core.testing)
}
