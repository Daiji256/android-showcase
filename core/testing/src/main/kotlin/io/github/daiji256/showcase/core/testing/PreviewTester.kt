package io.github.daiji256.showcase.core.testing

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.AndroidComposePreviewTester
import com.github.takahirom.roborazzi.ComposePreviewTester
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.RoborazziActivity
import com.github.takahirom.roborazzi.captureRoboImage
import com.github.takahirom.roborazzi.roborazziSystemPropertyImageExtension
import com.github.takahirom.roborazzi.roborazziSystemPropertyOutputDirectory
import com.github.takahirom.roborazzi.toRoborazziComposeOptions
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.screenshotid.AndroidPreviewScreenshotIdBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview

@Suppress("unused")
@OptIn(ExperimentalRoborazziApi::class)
class PreviewTester : ComposePreviewTester<AndroidPreviewInfo> by AndroidComposePreviewTester() {
    private val composeTestRule = createAndroidComposeRule<RoborazziActivity>()

    override fun options(): ComposePreviewTester.Options = super.options().copy(
        testLifecycleOptions = ComposePreviewTester.Options.JUnit4TestLifecycleOptions {
            composeTestRule
        },
    )

    override fun test(preview: ComposablePreview<AndroidPreviewInfo>) {
        val composable = preview.toRoborazziComposeOptions().configured(
            activityScenario = composeTestRule.activityRule.scenario,
        ) {
            CompositionLocalProvider(
                LocalInspectionMode provides true,
            ) {
                preview()
            }
        }
        composeTestRule.setContent(composable = composable)
        composeTestRule.onRoot().captureRoboImage(filePath = filePath(preview))
    }

    private fun filePath(preview: ComposablePreview<AndroidPreviewInfo>): String {
        val directory = roborazziSystemPropertyOutputDirectory()
        val name = AndroidPreviewScreenshotIdBuilder(preview).build()
        val extension = roborazziSystemPropertyImageExtension()
        return "$directory/$name.$extension"
    }
}
