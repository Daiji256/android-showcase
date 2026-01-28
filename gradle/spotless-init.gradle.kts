initscript {
    // renovate: datasource=maven depName=com.diffplug.spotless:spotless-plugin-gradle
    val spotlessVersion = "8.2.0"

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("com.diffplug.spotless:spotless-plugin-gradle:$spotlessVersion")
    }
}

rootProject {
    allprojects {
        // renovate: datasource=maven depName=com.pinterest.ktlint:ktlint-cli
        val ktlintVersion = "1.7.1"

        // renovate: datasource=maven depName=io.nlopez.compose.rules:ktlint
        val ktlintComposeRulesVersion = "0.5.6"

        apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
        extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension> {
            kotlin {
                target("**/*.kt")
                targetExclude("**/build/**/*.kt")
                ktlint(ktlintVersion)
                    .customRuleSets(
                        listOf(
                            "io.nlopez.compose.rules:ktlint:$ktlintComposeRulesVersion",
                        ),
                    )
                    .setEditorConfigPath(rootProject.file(".editorconfig"))
                    .editorConfigOverride(
                        mapOf(
                            "compose_allowed_composition_locals" to "LocalSnackbarHostState",
                        ),
                    )
            }

            kotlinGradle {
                target("**/*.kts")
                targetExclude("**/build/**/*.kts")
                ktlint(ktlintVersion)
            }
        }
    }
}
