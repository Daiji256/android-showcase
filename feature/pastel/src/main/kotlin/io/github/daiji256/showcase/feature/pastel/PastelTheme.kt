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
        primary = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 70f),
        onPrimary = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 100f),
        primaryContainer = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 80f),
        onPrimaryContainer = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 40f),
        primaryFixed = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 80f),
        primaryFixedDim = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 60f),
        onPrimaryFixed = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 40f),
        onPrimaryFixedVariant = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 50f),
        inversePrimary = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 90f),
        secondary = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 70f),
        onSecondary = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 100f),
        secondaryContainer = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 80f),
        onSecondaryContainer = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 40f),
        secondaryFixed = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 80f),
        secondaryFixedDim = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 60f),
        onSecondaryFixed = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 40f),
        onSecondaryFixedVariant = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 50f),
        tertiary = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 70f),
        onTertiary = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 100f),
        tertiaryContainer = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 80f),
        onTertiaryContainer = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 40f),
        tertiaryFixed = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 80f),
        tertiaryFixedDim = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 60f),
        onTertiaryFixed = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 40f),
        onTertiaryFixedVariant = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 50f),
        error = Color(hue = errorHue, chroma = ErrorChroma, tone = 60f),
        onError = Color(hue = errorHue, chroma = ErrorChroma, tone = 100f),
        errorContainer = Color(hue = errorHue, chroma = ErrorChroma, tone = 90f),
        onErrorContainer = Color(hue = errorHue, chroma = ErrorChroma, tone = 20f),
        surface = Color(hue = sourceHue, chroma = NeutralChroma, tone = 98f),
        surfaceDim = Color(hue = sourceHue, chroma = NeutralChroma, tone = 87f),
        surfaceBright = Color(hue = sourceHue, chroma = NeutralChroma, tone = 98f),
        onSurface = Color(hue = sourceHue, chroma = NeutralChroma, tone = 10f),
        surfaceTint = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 40f),
        surfaceVariant = Color(hue = sourceHue, chroma = NeutralChroma, tone = 90f),
        onSurfaceVariant = Color(hue = sourceHue, chroma = NeutralChroma, tone = 30f),
        surfaceContainerLowest = Color(hue = sourceHue, chroma = NeutralChroma, tone = 100f),
        surfaceContainerLow = Color(hue = sourceHue, chroma = NeutralChroma, tone = 96f),
        surfaceContainer = Color(hue = sourceHue, chroma = NeutralChroma, tone = 94f),
        surfaceContainerHigh = Color(hue = sourceHue, chroma = NeutralChroma, tone = 92f),
        surfaceContainerHighest = Color(hue = sourceHue, chroma = NeutralChroma, tone = 90f),
        inverseSurface = Color(hue = sourceHue, chroma = NeutralChroma, tone = 20f),
        inverseOnSurface = Color(hue = sourceHue, chroma = NeutralChroma, tone = 95f),
        background = Color(hue = sourceHue, chroma = NeutralChroma, tone = 98f),
        onBackground = Color(hue = sourceHue, chroma = NeutralChroma, tone = 10f),
        outline = Color(hue = sourceHue, chroma = NeutralChroma, tone = 50f),
        outlineVariant = Color(hue = sourceHue, chroma = NeutralChroma, tone = 80f),
        scrim = Color(hue = sourceHue, chroma = NeutralChroma, tone = 0f),
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
        primary = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 80f),
        onPrimary = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 35f),
        primaryContainer = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 40f),
        onPrimaryContainer = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 80f),
        primaryFixed = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 85f),
        primaryFixedDim = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 70f),
        onPrimaryFixed = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 30f),
        onPrimaryFixedVariant = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 40f),
        inversePrimary = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 40f),
        secondary = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 80f),
        onSecondary = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 35f),
        secondaryContainer = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 40f),
        onSecondaryContainer = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 80f),
        secondaryFixed = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 85f),
        secondaryFixedDim = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 70f),
        onSecondaryFixed = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 30f),
        onSecondaryFixedVariant = Color(hue = secondaryHue, chroma = SecondaryChroma, tone = 40f),
        tertiary = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 80f),
        onTertiary = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 35f),
        tertiaryContainer = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 40f),
        onTertiaryContainer = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 80f),
        tertiaryFixed = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 85f),
        tertiaryFixedDim = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 70f),
        onTertiaryFixed = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 30f),
        onTertiaryFixedVariant = Color(hue = tertiaryHue, chroma = TertiaryChroma, tone = 40f),
        error = Color(hue = errorHue, chroma = ErrorChroma, tone = 80f),
        onError = Color(hue = errorHue, chroma = ErrorChroma, tone = 20f),
        errorContainer = Color(hue = errorHue, chroma = ErrorChroma, tone = 30f),
        onErrorContainer = Color(hue = errorHue, chroma = ErrorChroma, tone = 85f),
        surface = Color(hue = sourceHue, chroma = NeutralChroma, tone = 15f),
        surfaceDim = Color(hue = sourceHue, chroma = NeutralChroma, tone = 15f),
        surfaceBright = Color(hue = sourceHue, chroma = NeutralChroma, tone = 32f),
        onSurface = Color(hue = sourceHue, chroma = NeutralChroma, tone = 90f),
        surfaceTint = Color(hue = sourceHue, chroma = PrimaryChroma, tone = 80f),
        surfaceVariant = Color(hue = sourceHue, chroma = NeutralChroma, tone = 34f),
        onSurfaceVariant = Color(hue = sourceHue, chroma = NeutralChroma, tone = 80f),
        surfaceContainerLowest = Color(hue = sourceHue, chroma = NeutralChroma, tone = 13f),
        surfaceContainerLow = Color(hue = sourceHue, chroma = NeutralChroma, tone = 19f),
        surfaceContainer = Color(hue = sourceHue, chroma = NeutralChroma, tone = 21f),
        surfaceContainerHigh = Color(hue = sourceHue, chroma = NeutralChroma, tone = 26f),
        surfaceContainerHighest = Color(hue = sourceHue, chroma = NeutralChroma, tone = 31f),
        inverseSurface = Color(hue = sourceHue, chroma = NeutralChroma, tone = 90f),
        inverseOnSurface = Color(hue = sourceHue, chroma = NeutralChroma, tone = 24f),
        background = Color(hue = sourceHue, chroma = NeutralChroma, tone = 15f),
        onBackground = Color(hue = sourceHue, chroma = NeutralChroma, tone = 90f),
        outline = Color(hue = sourceHue, chroma = NeutralChroma, tone = 70f),
        outlineVariant = Color(hue = sourceHue, chroma = NeutralChroma, tone = 40f),
        scrim = Color(hue = sourceHue, chroma = NeutralChroma, tone = 0f),
    )
}

@Stable
private fun Color(hue: Float, chroma: Float, tone: Float): Color =
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
