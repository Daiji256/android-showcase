package io.github.daiji256.showcase.core.testing

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryStd
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toAndroidRect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.DarkMode
import androidx.compose.ui.test.DeviceConfigurationOverride
import androidx.compose.ui.test.FontScale
import androidx.compose.ui.test.WindowInsets
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.then
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.roundToIntRect
import androidx.core.graphics.Insets
import androidx.core.view.WindowInsetsCompat
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
                    then DeviceConfigurationOverride.FontScale(fontScale = previewInfo.fontScale)
                    then DeviceConfigurationOverride.WindowInsets(windowInsets = windowInsets()),
            ) {
                CompositionLocalProvider(
                    LocalInspectionMode provides true,
                ) {
                    Box(modifier = Modifier.testTag("root")) {
                        testParameter.preview()
                        if (previewInfo.showSystemUi) {
                            SystemUi()
                        }
                    }
                }
            }
        }
        val filePath = filePath(preview = testParameter.preview)
        testParameter.composeTestRule.setContent(composable = composable)
        testParameter.composeTestRule.onNodeWithTag("root").captureRoboImage(filePath = filePath)
    }

    @Composable
    private fun windowInsets() =
        WindowInsetsCompat.Builder()
            .setInsets(
                WindowInsetsCompat.Type.statusBars(),
                DpRect(left = 0.dp, top = 24.dp, right = 0.dp, bottom = 0.dp).toInsets(),
            )
            .setInsets(
                WindowInsetsCompat.Type.navigationBars(),
                DpRect(left = 0.dp, top = 0.dp, right = 0.dp, bottom = 24.dp).toInsets(),
            )
            .build()

    @Composable
    private fun DpRect.toInsets(): Insets =
        Insets.of(with(LocalDensity.current) { toRect() }.roundToIntRect().toAndroidRect())

    val AndroidPreviewInfo.isDarkMode
        get() = uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

    @Composable
    private fun SystemUi() {
        Column {
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .padding(horizontal = 4.dp),
            ) {
                Text(
                    text = "15:00",
                    style = TextStyle(),
                    fontSize = with(LocalDensity.current) { 16.dp.toSp() },
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Wifi,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp),
                )
                Icon(
                    imageVector = Icons.Default.BatteryStd,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 4.dp)
                        .background(color = Color.Gray, shape = CircleShape),
                )
            }
        }
    }

    private fun filePath(preview: ComposablePreview<AndroidPreviewInfo>): String {
        val directory = roborazziSystemPropertyOutputDirectory()
        val name = AndroidPreviewScreenshotIdBuilder(preview).build()
        val extension = roborazziSystemPropertyImageExtension()
        return "$directory/$name.$extension"
    }
}
