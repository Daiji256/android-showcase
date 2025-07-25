package io.github.daiji256.showcase.core.testing

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.DarkMode
import androidx.compose.ui.test.DeviceConfigurationOverride
import androidx.compose.ui.test.FontScale
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.then
import com.github.takahirom.roborazzi.AndroidComposePreviewTester
import com.github.takahirom.roborazzi.ComposePreviewTester
import com.github.takahirom.roborazzi.ComposePreviewTester.TestParameter.JUnit4TestParameter.AndroidPreviewJUnit4TestParameter
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.captureRoboImage
import com.github.takahirom.roborazzi.roborazziSystemPropertyImageExtension
import com.github.takahirom.roborazzi.roborazziSystemPropertyOutputDirectory
import com.github.takahirom.roborazzi.toRoborazziComposeOptions
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.screenshotid.AndroidPreviewScreenshotIdBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview

@Suppress("unused")
@OptIn(ExperimentalRoborazziApi::class)
class PreviewTester :
    ComposePreviewTester<AndroidPreviewJUnit4TestParameter> by AndroidComposePreviewTester() {
    override fun options(): ComposePreviewTester.Options = super.options().copy(
        testLifecycleOptions = ComposePreviewTester.Options.JUnit4TestLifecycleOptions(
            testRuleFactory = { composeTestRule ->
                composeTestRule
            },
        ),
    )

    override fun test(testParameter: AndroidPreviewJUnit4TestParameter) {
        val composable = testParameter.preview.toRoborazziComposeOptions().configured(
            activityScenario = testParameter.composeTestRule.activityRule.scenario,
        ) {
            val previewInfo = testParameter.preview.previewInfo
            DeviceConfigurationOverride(
                override = DeviceConfigurationOverride.DarkMode(isDarkMode = previewInfo.isDarkMode)
                    then DeviceConfigurationOverride.FontScale(fontScale = previewInfo.fontScale),
            ) {
                CompositionLocalProvider(
                    LocalInspectionMode provides true,
                ) {
                    Box(modifier = Modifier.testTag("root")) {
                        testParameter.preview()
                    }
                }
            }
        }
        val filePath = filePath(preview = testParameter.preview)
        testParameter.composeTestRule.setContent(composable = composable)
        testParameter.composeTestRule.onNodeWithTag("root").captureRoboImage(filePath = filePath)
    }

    val AndroidPreviewInfo.isDarkMode
        get() = uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

    private fun filePath(preview: ComposablePreview<AndroidPreviewInfo>): String {
        val directory = roborazziSystemPropertyOutputDirectory()
        val name = AndroidPreviewScreenshotIdBuilder(preview).build()
        val extension = roborazziSystemPropertyImageExtension()
        return "$directory/$name.$extension"
    }
}
