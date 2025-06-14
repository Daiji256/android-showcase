pluginManagement {
    includeBuild("build-logic")
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

rootProject.name = "showcase"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:common")
include(":core:designsystem")
include(":core:testing")
include(":core:ui")
include(":feature:customtabs")
include(":feature:ktlint")
include(":feature:navigationarguments")
include(":feature:roborazzi")
include(":feature:safeurihandler")
