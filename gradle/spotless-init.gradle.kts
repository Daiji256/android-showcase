initscript {
    val spotlessVersion = "8.2.0"

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("com.diffplug.spotless:spotless-plugin-gradle:$spotlessVersion")
    }
}

rootProject {
    subprojects {
        val ktlintVersion = "1.7.1"
        val ktlintComposeRulesVersion = "0.4.26"

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