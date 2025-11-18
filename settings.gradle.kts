pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "showcase"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:common")
include(":core:designsystem")
include(":core:testing")
include(":core:ui")
include(":feature:customtabs")
include(":feature:hiltcomposable")
include(":feature:ktlint")
include(":feature:localsnackbarhoststate")
include(":feature:navigationarguments")
include(":feature:roborazzi")
include(":feature:safeurihandler")
include(":feature:systemstyle")

includeBuild("build-logic")
