package io.github.daiji256.showcase.core.ui.window

import android.view.RoundedCorner
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.runtime.compositionLocalWithComputedDefaultOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalView

val LocalWindowShape = compositionLocalWithComputedDefaultOf<Shape> {
    val view = LocalView.currentValue
    val insets = view.rootWindowInsets
    val topLeftCorner = insets?.getRoundedCorner(RoundedCorner.POSITION_TOP_LEFT)
    val topRightCorner = insets?.getRoundedCorner(RoundedCorner.POSITION_TOP_RIGHT)
    val bottomRightCorner = insets?.getRoundedCorner(RoundedCorner.POSITION_BOTTOM_RIGHT)
    val bottomLeftCorner = insets?.getRoundedCorner(RoundedCorner.POSITION_BOTTOM_LEFT)
    AbsoluteRoundedCornerShape(
        topLeft = topLeftCorner?.radius?.toFloat() ?: 0f,
        topRight = topRightCorner?.radius?.toFloat() ?: 0f,
        bottomRight = bottomRightCorner?.radius?.toFloat() ?: 0f,
        bottomLeft = bottomLeftCorner?.radius?.toFloat() ?: 0f,
    )
}
