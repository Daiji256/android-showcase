package io.github.daiji256.showcase.feature.unstyled.colorcontrast

import android.app.UiModeManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun currentColorContrast(): ColorContrast {
    val context = LocalContext.current
    val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    val contrast = uiModeManager.contrast
    return when {
        contrast <= 1.0f / 3.0f -> ColorContrast.Default
        contrast <= 2.0f / 3.0f -> ColorContrast.Medium
        else -> ColorContrast.High
    }
}
