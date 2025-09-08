package io.github.daiji256.showcase.feature.unstyled

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.composeunstyled.Text
import io.github.daiji256.showcase.feature.unstyled.colorcontrast.ColorContrast
import io.github.daiji256.showcase.feature.unstyled.colorcontrast.currentColorContrast
import io.github.daiji256.showcase.feature.unstyled.systemcolor.primaryColor
import io.github.daiji256.showcase.feature.unstyled.systemcolor.secondaryColor
import io.github.daiji256.showcase.feature.unstyled.systemcolor.tertiaryColor

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
            stringResource(id = R.string.feature_unstyled_label_value_separator)

        Row {
            Text(
                text = stringResource(id = R.string.feature_unstyled_theme_label) +
                    labelValueSeparator,
            )
            Text(
                text = when (isSystemInDarkTheme()) {
                    false -> stringResource(id = R.string.feature_unstyled_theme_value_light)
                    true -> stringResource(id = R.string.feature_unstyled_theme_value_dark)
                },
            )
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            Row {
                Text(
                    text = stringResource(id = R.string.feature_unstyled_color_contrast_label) +
                        labelValueSeparator,
                )
                Text(
                    text = when (currentColorContrast()) {
                        ColorContrast.Default ->
                            stringResource(
                                id = R.string.feature_unstyled_color_contrast_value_default,
                            )

                        ColorContrast.Medium ->
                            stringResource(
                                id = R.string.feature_unstyled_color_contrast_value_medium,
                            )

                        ColorContrast.High ->
                            stringResource(
                                id = R.string.feature_unstyled_color_contrast_value_high,
                            )
                    },
                )
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            listOf(
                Pair(
                    stringResource(id = R.string.feature_unstyled_primary_color_label),
                    primaryColor(),
                ),
                Pair(
                    stringResource(id = R.string.feature_unstyled_secondary_color_label),
                    secondaryColor(),
                ),
                Pair(
                    stringResource(id = R.string.feature_unstyled_tertiary_color_label),
                    tertiaryColor(),
                ),
            ).forEach { (label, color) ->
                Row(
                    modifier = Modifier
                        .height(IntrinsicSize.Max),
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append(label + labelValueSeparator)
                            withStyle(SpanStyle(fontFamily = FontFamily.Monospace)) {
                                append(
                                    stringResource(
                                        id = R.string.feature_unstyled_color_value,
                                        color.toArgb() and 0xFFFFFF,
                                    ),
                                )
                            }
                        },
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
