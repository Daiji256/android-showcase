package io.github.daiji256.showcase.feature.systemstyle

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import io.github.daiji256.showcase.feature.systemstyle.colorcontrast.ColorContrast
import io.github.daiji256.showcase.feature.systemstyle.colorcontrast.currentColorContrast
import io.github.daiji256.showcase.feature.systemstyle.systemcolor.primaryColor
import io.github.daiji256.showcase.feature.systemstyle.systemcolor.secondaryColor
import io.github.daiji256.showcase.feature.systemstyle.systemcolor.tertiaryColor

@Composable
internal fun SystemStyleValues(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .background(color = Color.White)
            .border(width = 1.dp, color = Color.Black)
            .padding(4.dp),
    ) {
        val labelValueSeparator =
            stringResource(id = R.string.feature_system_style_label_value_separator)

        Row {
            BasicText(
                text = stringResource(id = R.string.feature_system_style_theme_label) +
                    labelValueSeparator,
                style = TextStyle(color = Color.Black),
            )
            BasicText(
                text = when (isSystemInDarkTheme()) {
                    false -> stringResource(id = R.string.feature_system_style_theme_value_light)
                    true -> stringResource(id = R.string.feature_system_style_theme_value_dark)
                },
                style = TextStyle(color = Color.Black),
            )
        }

        Row {
            val density = LocalDensity.current
            BasicText(
                text = stringResource(id = R.string.feature_system_style_font_scale_label) +
                    labelValueSeparator,
                style = TextStyle(color = Color.Black),
            )
            BasicText(
                text = stringResource(
                    id = R.string.feature_system_style_font_scale_value,
                    density.fontScale,
                ),
                style = TextStyle(color = Color.Black),
            )
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            Row {
                BasicText(
                    text = stringResource(id = R.string.feature_system_style_color_contrast_label) +
                        labelValueSeparator,
                    style = TextStyle(color = Color.Black),
                )
                BasicText(
                    text = when (currentColorContrast()) {
                        ColorContrast.Default ->
                            stringResource(
                                id = R.string.feature_system_style_color_contrast_value_default,
                            )

                        ColorContrast.Medium ->
                            stringResource(
                                id = R.string.feature_system_style_color_contrast_value_medium,
                            )

                        ColorContrast.High ->
                            stringResource(
                                id = R.string.feature_system_style_color_contrast_value_high,
                            )
                    },
                    style = TextStyle(color = Color.Black),
                )
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            listOf(
                Pair(
                    stringResource(id = R.string.feature_system_style_primary_color_label),
                    primaryColor(),
                ),
                Pair(
                    stringResource(id = R.string.feature_system_style_secondary_color_label),
                    secondaryColor(),
                ),
                Pair(
                    stringResource(id = R.string.feature_system_style_tertiary_color_label),
                    tertiaryColor(),
                ),
            ).forEach { (label, color) ->
                Row(
                    modifier = Modifier
                        .height(IntrinsicSize.Max),
                ) {
                    BasicText(
                        text = buildAnnotatedString {
                            append(label + labelValueSeparator)
                            withStyle(SpanStyle(fontFamily = FontFamily.Monospace)) {
                                append(
                                    stringResource(
                                        id = R.string.feature_system_style_color_value,
                                        color.toArgb() and 0xFFFFFF,
                                    ),
                                )
                            }
                        },
                        style = TextStyle(color = Color.Black),
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .background(color = color),
                    )
                }
            }
        }
    }
}
