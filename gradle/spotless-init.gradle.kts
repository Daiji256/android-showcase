initscript {
    // renovate: datasource=maven depName=com.diffplug.spotless:spotless-plugin-gradle
    val spotlessVersion = "8.2.1"

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("com.diffplug.spotless:spotless-plugin-gradle:$spotlessVersion")
    }
}

rootProject {
    // renovate: datasource=maven depName=com.pinterest.ktlint:ktlint-cli
    val ktlintVersion = "1.8.0"

    // renovate: datasource=maven depName=io.nlopez.compose.rules:ktlint
    val ktlintComposeRulesVersion = "0.5.6"

    val editorConfigOverride = mapOf(
        "ktlint_code_style" to "android_studio",
        "ktlint_experimental" to "enabled",
        "ktlint_function_naming_ignore_when_annotated_with" to "Composable,Test",
        "ktlint_standard_class-signature" to "disabled",
        "ktlint_standard_function-signature" to "disabled",
        "ktlint_standard_property-naming" to "disabled",
        "compose_allowed_composition_locals" to "LocalSnackbarHostState",
    )

    apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
    extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint(ktlintVersion)
                .editorConfigOverride(editorConfigOverride)
                .customRuleSets(
                    listOf(
                        "io.nlopez.compose.rules:ktlint:$ktlintComposeRulesVersion",
                    ),
                )
        }

        kotlinGradle {
            target("**/*.kts")
            targetExclude("**/build/**/*.kts")
            ktlint(ktlintVersion)
                .editorConfigOverride(editorConfigOverride)
        }
    }
}
