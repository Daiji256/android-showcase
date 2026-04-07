package io.github.daiji256.showcase.feature.pastel

import android.content.Context
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.ColorUtils

@Composable
internal fun PastelTheme(
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val colorScheme = when {
        isSystemInDarkTheme() -> dynamicDarkPastelColorScheme(context = context)
        else -> dynamicLightPastelColorScheme(context = context)
    }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}

@Stable
private fun dynamicLightPastelColorScheme(context: Context): ColorScheme {
    val mdColorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        dynamicLightColorScheme(context = context)
    } else {
        lightColorScheme()
    }
    val sourceHue = mdColorScheme.primary.m3Hue()
    val secondaryHue = mdColorScheme.secondary.m3Hue()
    val tertiaryHue = mdColorScheme.tertiary.m3Hue()
    val errorHue = mdColorScheme.error.m3Hue()

    // TODO: contrast
    return ColorScheme(
        primary = m3HctColor(sourceHue, PrimaryChroma, 70f),
        onPrimary = m3HctColor(sourceHue, PrimaryChroma, 100f),
        primaryContainer = m3HctColor(sourceHue, PrimaryChroma, 80f),
        onPrimaryContainer = m3HctColor(sourceHue, PrimaryChroma, 40f),
        primaryFixed = m3HctColor(sourceHue, PrimaryChroma, 80f),
        primaryFixedDim = m3HctColor(sourceHue, PrimaryChroma, 60f),
        onPrimaryFixed = m3HctColor(sourceHue, PrimaryChroma, 40f),
        onPrimaryFixedVariant = m3HctColor(sourceHue, PrimaryChroma, 50f),
        inversePrimary = m3HctColor(sourceHue, PrimaryChroma, 90f),
        secondary = m3HctColor(secondaryHue, SecondaryChroma, 70f),
        onSecondary = m3HctColor(secondaryHue, SecondaryChroma, 100f),
        secondaryContainer = m3HctColor(secondaryHue, SecondaryChroma, 80f),
        onSecondaryContainer = m3HctColor(secondaryHue, SecondaryChroma, 40f),
        secondaryFixed = m3HctColor(secondaryHue, SecondaryChroma, 80f),
        secondaryFixedDim = m3HctColor(secondaryHue, SecondaryChroma, 60f),
        onSecondaryFixed = m3HctColor(secondaryHue, SecondaryChroma, 40f),
        onSecondaryFixedVariant = m3HctColor(secondaryHue, SecondaryChroma, 50f),
        tertiary = m3HctColor(tertiaryHue, TertiaryChroma, 70f),
        onTertiary = m3HctColor(tertiaryHue, TertiaryChroma, 100f),
        tertiaryContainer = m3HctColor(tertiaryHue, TertiaryChroma, 80f),
        onTertiaryContainer = m3HctColor(tertiaryHue, TertiaryChroma, 40f),
        tertiaryFixed = m3HctColor(tertiaryHue, TertiaryChroma, 80f),
        tertiaryFixedDim = m3HctColor(tertiaryHue, TertiaryChroma, 60f),
        onTertiaryFixed = m3HctColor(tertiaryHue, TertiaryChroma, 40f),
        onTertiaryFixedVariant = m3HctColor(tertiaryHue, TertiaryChroma, 50f),
        error = m3HctColor(errorHue, ErrorChroma, 60f),
        onError = m3HctColor(errorHue, ErrorChroma, 100f),
        errorContainer = m3HctColor(errorHue, ErrorChroma, 90f),
        onErrorContainer = m3HctColor(errorHue, ErrorChroma, 20f),
        surface = m3HctColor(sourceHue, NeutralChroma, 98f),
        surfaceDim = m3HctColor(sourceHue, NeutralChroma, 87f),
        surfaceBright = m3HctColor(sourceHue, NeutralChroma, 98f),
        onSurface = m3HctColor(sourceHue, NeutralChroma, 10f),
        surfaceTint = m3HctColor(sourceHue, PrimaryChroma, 40f),
        surfaceVariant = m3HctColor(sourceHue, NeutralChroma, 90f),
        onSurfaceVariant = m3HctColor(sourceHue, NeutralChroma, 30f),
        surfaceContainerLowest = m3HctColor(sourceHue, NeutralChroma, 100f),
        surfaceContainerLow = m3HctColor(sourceHue, NeutralChroma, 96f),
        surfaceContainer = m3HctColor(sourceHue, NeutralChroma, 94f),
        surfaceContainerHigh = m3HctColor(sourceHue, NeutralChroma, 92f),
        surfaceContainerHighest = m3HctColor(sourceHue, NeutralChroma, 90f),
        inverseSurface = m3HctColor(sourceHue, NeutralChroma, 20f),
        inverseOnSurface = m3HctColor(sourceHue, NeutralChroma, 95f),
        background = m3HctColor(sourceHue, NeutralChroma, 98f),
        onBackground = m3HctColor(sourceHue, NeutralChroma, 10f),
        outline = m3HctColor(sourceHue, NeutralChroma, 50f),
        outlineVariant = m3HctColor(sourceHue, NeutralChroma, 80f),
        scrim = m3HctColor(sourceHue, NeutralChroma, 0f),
    )
}

@Stable
private fun dynamicDarkPastelColorScheme(context: Context): ColorScheme {
    val mdColorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        dynamicDarkColorScheme(context = context)
    } else {
        darkColorScheme()
    }
    val sourceHue = mdColorScheme.primary.m3Hue()
    val secondaryHue = mdColorScheme.secondary.m3Hue()
    val tertiaryHue = mdColorScheme.tertiary.m3Hue()
    val errorHue = mdColorScheme.error.m3Hue()

    // TODO: contrast
    return ColorScheme(
        primary = m3HctColor(sourceHue, PrimaryChroma, 80f),
        onPrimary = m3HctColor(sourceHue, PrimaryChroma, 35f),
        primaryContainer = m3HctColor(sourceHue, PrimaryChroma, 40f),
        onPrimaryContainer = m3HctColor(sourceHue, PrimaryChroma, 80f),
        primaryFixed = m3HctColor(sourceHue, PrimaryChroma, 85f),
        primaryFixedDim = m3HctColor(sourceHue, PrimaryChroma, 70f),
        onPrimaryFixed = m3HctColor(sourceHue, PrimaryChroma, 30f),
        onPrimaryFixedVariant = m3HctColor(sourceHue, PrimaryChroma, 40f),
        inversePrimary = m3HctColor(sourceHue, PrimaryChroma, 40f),
        secondary = m3HctColor(secondaryHue, SecondaryChroma, 80f),
        onSecondary = m3HctColor(secondaryHue, SecondaryChroma, 35f),
        secondaryContainer = m3HctColor(secondaryHue, SecondaryChroma, 40f),
        onSecondaryContainer = m3HctColor(secondaryHue, SecondaryChroma, 80f),
        secondaryFixed = m3HctColor(secondaryHue, SecondaryChroma, 85f),
        secondaryFixedDim = m3HctColor(secondaryHue, SecondaryChroma, 70f),
        onSecondaryFixed = m3HctColor(secondaryHue, SecondaryChroma, 30f),
        onSecondaryFixedVariant = m3HctColor(secondaryHue, SecondaryChroma, 40f),
        tertiary = m3HctColor(tertiaryHue, TertiaryChroma, 80f),
        onTertiary = m3HctColor(tertiaryHue, TertiaryChroma, 35f),
        tertiaryContainer = m3HctColor(tertiaryHue, TertiaryChroma, 40f),
        onTertiaryContainer = m3HctColor(tertiaryHue, TertiaryChroma, 80f),
        tertiaryFixed = m3HctColor(tertiaryHue, TertiaryChroma, 85f),
        tertiaryFixedDim = m3HctColor(tertiaryHue, TertiaryChroma, 70f),
        onTertiaryFixed = m3HctColor(tertiaryHue, TertiaryChroma, 30f),
        onTertiaryFixedVariant = m3HctColor(tertiaryHue, TertiaryChroma, 40f),
        error = m3HctColor(errorHue, ErrorChroma, 80f),
        onError = m3HctColor(errorHue, ErrorChroma, 20f),
        errorContainer = m3HctColor(errorHue, ErrorChroma, 30f),
        onErrorContainer = m3HctColor(errorHue, ErrorChroma, 85f),
        surface = m3HctColor(sourceHue, NeutralChroma, 15f),
        surfaceDim = m3HctColor(sourceHue, NeutralChroma, 15f),
        surfaceBright = m3HctColor(sourceHue, NeutralChroma, 32f),
        onSurface = m3HctColor(sourceHue, NeutralChroma, 90f),
        surfaceTint = m3HctColor(sourceHue, PrimaryChroma, 80f),
        surfaceVariant = m3HctColor(sourceHue, NeutralChroma, 34f),
        onSurfaceVariant = m3HctColor(sourceHue, NeutralChroma, 80f),
        surfaceContainerLowest = m3HctColor(sourceHue, NeutralChroma, 13f),
        surfaceContainerLow = m3HctColor(sourceHue, NeutralChroma, 19f),
        surfaceContainer = m3HctColor(sourceHue, NeutralChroma, 21f),
        surfaceContainerHigh = m3HctColor(sourceHue, NeutralChroma, 26f),
        surfaceContainerHighest = m3HctColor(sourceHue, NeutralChroma, 31f),
        inverseSurface = m3HctColor(sourceHue, NeutralChroma, 90f),
        inverseOnSurface = m3HctColor(sourceHue, NeutralChroma, 24f),
        background = m3HctColor(sourceHue, NeutralChroma, 15f),
        onBackground = m3HctColor(sourceHue, NeutralChroma, 90f),
        outline = m3HctColor(sourceHue, NeutralChroma, 70f),
        outlineVariant = m3HctColor(sourceHue, NeutralChroma, 40f),
        scrim = m3HctColor(sourceHue, NeutralChroma, 0f),
    )
}

@Stable
private fun m3HctColor(hue: Float, chroma: Float, tone: Float): Color =
    Color(ColorUtils.M3HCTToColor(hue, chroma, tone))

@Stable
private fun Color.m3Hue(): Float {
    val m3hct = FloatArray(3)
    ColorUtils.colorToM3HCT(this.toArgb(), m3hct)
    return m3hct[0]
}

private const val PrimaryChroma = 50f
private const val SecondaryChroma = 30f
private const val TertiaryChroma = 40f
private const val NeutralChroma = 8f
private const val ErrorChroma = 70f
