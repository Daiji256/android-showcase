initscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("com.diffplug.spotless:spotless-plugin-gradle:8.4.0")
    }
}

rootProject {
    // renovate: datasource=maven depName=com.pinterest.ktlint:ktlint-cli
    val ktlintVersion = "1.8.0"

    // renovate: datasource=maven depName=io.nlopez.compose.rules:ktlint
    val ktlintComposeRulesVersion = "0.5.7"

    val editorConfigOverride = mapOf(
        "ktlint_code_style" to "android_studio",
        "ktlint_experimental" to "enabled",
        "ktlint_function_naming_ignore_when_annotated_with" to "Composable,Test",
        "ktlint_standard_class-signature" to "disabled",
        "ktlint_standard_function-signature" to "disabled",
        "ktlint_standard_property-naming" to "disabled",
        "compose_allowed_composition_locals" to
            "LocalNavigator,LocalSnackbarHostState,LocalColorContrast",
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
