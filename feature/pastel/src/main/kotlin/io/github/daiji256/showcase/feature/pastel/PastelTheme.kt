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
    val primaryHue = mdColorScheme.primary.m3Hue()
    val secondaryHue = mdColorScheme.secondary.m3Hue()
    val tertiaryHue = mdColorScheme.tertiary.m3Hue()
    val errorHue = mdColorScheme.error.m3Hue()

    // TODO: contrast
    return ColorScheme(
        primary = Color.m3Hct(primaryHue, PrimaryChroma, 70f),
        onPrimary = Color.m3Hct(primaryHue, PrimaryChroma, 100f),
        primaryContainer = Color.m3Hct(primaryHue, PrimaryChroma, 80f),
        onPrimaryContainer = Color.m3Hct(primaryHue, PrimaryChroma, 40f),
        primaryFixed = Color.m3Hct(primaryHue, PrimaryChroma, 80f),
        primaryFixedDim = Color.m3Hct(primaryHue, PrimaryChroma, 60f),
        onPrimaryFixed = Color.m3Hct(primaryHue, PrimaryChroma, 40f),
        onPrimaryFixedVariant = Color.m3Hct(primaryHue, PrimaryChroma, 50f),
        inversePrimary = Color.m3Hct(primaryHue, PrimaryChroma, 90f),
        secondary = Color.m3Hct(secondaryHue, SecondaryChroma, 70f),
        onSecondary = Color.m3Hct(secondaryHue, SecondaryChroma, 100f),
        secondaryContainer = Color.m3Hct(secondaryHue, SecondaryChroma, 80f),
        onSecondaryContainer = Color.m3Hct(secondaryHue, SecondaryChroma, 40f),
        secondaryFixed = Color.m3Hct(secondaryHue, SecondaryChroma, 80f),
        secondaryFixedDim = Color.m3Hct(secondaryHue, SecondaryChroma, 60f),
        onSecondaryFixed = Color.m3Hct(secondaryHue, SecondaryChroma, 40f),
        onSecondaryFixedVariant = Color.m3Hct(secondaryHue, SecondaryChroma, 50f),
        tertiary = Color.m3Hct(tertiaryHue, TertiaryChroma, 70f),
        onTertiary = Color.m3Hct(tertiaryHue, TertiaryChroma, 100f),
        tertiaryContainer = Color.m3Hct(tertiaryHue, TertiaryChroma, 80f),
        onTertiaryContainer = Color.m3Hct(tertiaryHue, TertiaryChroma, 40f),
        tertiaryFixed = Color.m3Hct(tertiaryHue, TertiaryChroma, 80f),
        tertiaryFixedDim = Color.m3Hct(tertiaryHue, TertiaryChroma, 60f),
        onTertiaryFixed = Color.m3Hct(tertiaryHue, TertiaryChroma, 40f),
        onTertiaryFixedVariant = Color.m3Hct(tertiaryHue, TertiaryChroma, 50f),
        error = Color.m3Hct(errorHue, ErrorChroma, 60f),
        onError = Color.m3Hct(errorHue, ErrorChroma, 100f),
        errorContainer = Color.m3Hct(errorHue, ErrorChroma, 90f),
        onErrorContainer = Color.m3Hct(errorHue, ErrorChroma, 20f),
        surface = Color.m3Hct(primaryHue, NeutralChroma, 98f),
        surfaceDim = Color.m3Hct(primaryHue, NeutralChroma, 87f),
        surfaceBright = Color.m3Hct(primaryHue, NeutralChroma, 98f),
        onSurface = Color.m3Hct(primaryHue, NeutralChroma, 10f),
        surfaceTint = Color.m3Hct(primaryHue, PrimaryChroma, 40f),
        surfaceVariant = Color.m3Hct(primaryHue, NeutralChroma, 90f),
        onSurfaceVariant = Color.m3Hct(primaryHue, NeutralChroma, 30f),
        surfaceContainerLowest = Color.m3Hct(primaryHue, NeutralChroma, 100f),
        surfaceContainerLow = Color.m3Hct(primaryHue, NeutralChroma, 96f),
        surfaceContainer = Color.m3Hct(primaryHue, NeutralChroma, 94f),
        surfaceContainerHigh = Color.m3Hct(primaryHue, NeutralChroma, 92f),
        surfaceContainerHighest = Color.m3Hct(primaryHue, NeutralChroma, 90f),
        inverseSurface = Color.m3Hct(primaryHue, NeutralChroma, 20f),
        inverseOnSurface = Color.m3Hct(primaryHue, NeutralChroma, 95f),
        background = Color.m3Hct(primaryHue, NeutralChroma, 98f),
        onBackground = Color.m3Hct(primaryHue, NeutralChroma, 10f),
        outline = Color.m3Hct(primaryHue, NeutralChroma, 50f),
        outlineVariant = Color.m3Hct(primaryHue, NeutralChroma, 80f),
        scrim = Color.m3Hct(primaryHue, NeutralChroma, 0f),
    )
}

@Stable
private fun dynamicDarkPastelColorScheme(context: Context): ColorScheme {
    val mdColorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        dynamicDarkColorScheme(context = context)
    } else {
        darkColorScheme()
    }
    val primaryHue = mdColorScheme.primary.m3Hue()
    val secondaryHue = mdColorScheme.secondary.m3Hue()
    val tertiaryHue = mdColorScheme.tertiary.m3Hue()
    val errorHue = mdColorScheme.error.m3Hue()

    // TODO: contrast
    return ColorScheme(
        primary = Color.m3Hct(primaryHue, PrimaryChroma, 80f),
        onPrimary = Color.m3Hct(primaryHue, PrimaryChroma, 35f),
        primaryContainer = Color.m3Hct(primaryHue, PrimaryChroma, 40f),
        onPrimaryContainer = Color.m3Hct(primaryHue, PrimaryChroma, 80f),
        primaryFixed = Color.m3Hct(primaryHue, PrimaryChroma, 85f),
        primaryFixedDim = Color.m3Hct(primaryHue, PrimaryChroma, 70f),
        onPrimaryFixed = Color.m3Hct(primaryHue, PrimaryChroma, 30f),
        onPrimaryFixedVariant = Color.m3Hct(primaryHue, PrimaryChroma, 40f),
        inversePrimary = Color.m3Hct(primaryHue, PrimaryChroma, 40f),
        secondary = Color.m3Hct(secondaryHue, SecondaryChroma, 80f),
        onSecondary = Color.m3Hct(secondaryHue, SecondaryChroma, 35f),
        secondaryContainer = Color.m3Hct(secondaryHue, SecondaryChroma, 40f),
        onSecondaryContainer = Color.m3Hct(secondaryHue, SecondaryChroma, 80f),
        secondaryFixed = Color.m3Hct(secondaryHue, SecondaryChroma, 85f),
        secondaryFixedDim = Color.m3Hct(secondaryHue, SecondaryChroma, 70f),
        onSecondaryFixed = Color.m3Hct(secondaryHue, SecondaryChroma, 30f),
        onSecondaryFixedVariant = Color.m3Hct(secondaryHue, SecondaryChroma, 40f),
        tertiary = Color.m3Hct(tertiaryHue, TertiaryChroma, 80f),
        onTertiary = Color.m3Hct(tertiaryHue, TertiaryChroma, 35f),
        tertiaryContainer = Color.m3Hct(tertiaryHue, TertiaryChroma, 40f),
        onTertiaryContainer = Color.m3Hct(tertiaryHue, TertiaryChroma, 80f),
        tertiaryFixed = Color.m3Hct(tertiaryHue, TertiaryChroma, 85f),
        tertiaryFixedDim = Color.m3Hct(tertiaryHue, TertiaryChroma, 70f),
        onTertiaryFixed = Color.m3Hct(tertiaryHue, TertiaryChroma, 30f),
        onTertiaryFixedVariant = Color.m3Hct(tertiaryHue, TertiaryChroma, 40f),
        error = Color.m3Hct(errorHue, ErrorChroma, 80f),
        onError = Color.m3Hct(errorHue, ErrorChroma, 20f),
        errorContainer = Color.m3Hct(errorHue, ErrorChroma, 30f),
        onErrorContainer = Color.m3Hct(errorHue, ErrorChroma, 85f),
        surface = Color.m3Hct(primaryHue, NeutralChroma, 15f),
        surfaceDim = Color.m3Hct(primaryHue, NeutralChroma, 15f),
        surfaceBright = Color.m3Hct(primaryHue, NeutralChroma, 32f),
        onSurface = Color.m3Hct(primaryHue, NeutralChroma, 90f),
        surfaceTint = Color.m3Hct(primaryHue, PrimaryChroma, 80f),
        surfaceVariant = Color.m3Hct(primaryHue, NeutralChroma, 34f),
        onSurfaceVariant = Color.m3Hct(primaryHue, NeutralChroma, 80f),
        surfaceContainerLowest = Color.m3Hct(primaryHue, NeutralChroma, 13f),
        surfaceContainerLow = Color.m3Hct(primaryHue, NeutralChroma, 19f),
        surfaceContainer = Color.m3Hct(primaryHue, NeutralChroma, 21f),
        surfaceContainerHigh = Color.m3Hct(primaryHue, NeutralChroma, 26f),
        surfaceContainerHighest = Color.m3Hct(primaryHue, NeutralChroma, 31f),
        inverseSurface = Color.m3Hct(primaryHue, NeutralChroma, 90f),
        inverseOnSurface = Color.m3Hct(primaryHue, NeutralChroma, 24f),
        background = Color.m3Hct(primaryHue, NeutralChroma, 15f),
        onBackground = Color.m3Hct(primaryHue, NeutralChroma, 90f),
        outline = Color.m3Hct(primaryHue, NeutralChroma, 70f),
        outlineVariant = Color.m3Hct(primaryHue, NeutralChroma, 40f),
        scrim = Color.m3Hct(primaryHue, NeutralChroma, 0f),
    )
}

@Stable
private fun Color.Companion.m3Hct(hue: Float, chroma: Float, tone: Float): Color =
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
