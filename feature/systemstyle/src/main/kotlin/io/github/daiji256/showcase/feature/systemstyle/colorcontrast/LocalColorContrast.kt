package io.github.daiji256.showcase.feature.systemstyle.colorcontrast

import android.app.UiModeManager
import android.content.Context
import androidx.compose.runtime.compositionLocalWithComputedDefaultOf
import androidx.compose.ui.platform.LocalContext

internal val LocalColorContrast = compositionLocalWithComputedDefaultOf {
    val context = LocalContext.currentValue
    val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    val contrast = uiModeManager.contrast
    when {
        contrast <= 1.0f / 3.0f -> ColorContrast.Default
        contrast <= 2.0f / 3.0f -> ColorContrast.Medium
        else -> ColorContrast.High
    }
}
