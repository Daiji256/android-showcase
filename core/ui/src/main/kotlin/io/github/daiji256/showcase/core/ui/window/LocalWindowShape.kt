package io.github.daiji256.showcase.core.ui.window

import android.os.Build
import android.view.RoundedCorner
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.compositionLocalWithComputedDefaultOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.LayoutDirection

val LocalWindowShape = compositionLocalWithComputedDefaultOf {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
        return@compositionLocalWithComputedDefaultOf RectangleShape
    }

    val view = LocalView.currentValue
    val insets = view.rootWindowInsets
    val topLeft = insets?.getRoundedCorner(RoundedCorner.POSITION_TOP_LEFT)?.radius ?: 0
    val topRight = insets?.getRoundedCorner(RoundedCorner.POSITION_TOP_RIGHT)?.radius ?: 0
    val bottomRight = insets?.getRoundedCorner(RoundedCorner.POSITION_BOTTOM_RIGHT)?.radius ?: 0
    val bottomLeft = insets?.getRoundedCorner(RoundedCorner.POSITION_BOTTOM_LEFT)?.radius ?: 0
    when (LocalLayoutDirection.currentValue) {
        LayoutDirection.Ltr ->
            RoundedCornerShape(
                topStart = topLeft.toFloat(),
                topEnd = topRight.toFloat(),
                bottomEnd = bottomRight.toFloat(),
                bottomStart = bottomLeft.toFloat(),
            )

        LayoutDirection.Rtl ->
            RoundedCornerShape(
                topStart = topRight.toFloat(),
                topEnd = topLeft.toFloat(),
                bottomEnd = bottomLeft.toFloat(),
                bottomStart = bottomRight.toFloat(),
            )
    }
}
