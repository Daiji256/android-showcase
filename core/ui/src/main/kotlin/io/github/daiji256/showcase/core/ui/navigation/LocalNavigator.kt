package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.compositionLocalOf

val LocalNavigator = compositionLocalOf<Navigator> {
    error("CompositionLocal LocalNavigator not present")
}
