package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation3.runtime.NavKey

val LocalNavigator = compositionLocalOf<Navigator<NavKey>> {
    error("CompositionLocal LocalNavigator not present")
}
