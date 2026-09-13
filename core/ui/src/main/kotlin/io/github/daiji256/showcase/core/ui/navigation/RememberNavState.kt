package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.navigation3.runtime.NavKey

/**
 * Remember a [NavState].
 *
 * @param start the start [NavKey]
 * @param pending the pending keys to navigate up
 * @return a remembered [NavState]
 */
@OptIn(InternalNavStateApi::class)
@Composable
fun rememberNavState(start: NavKey, pending: List<NavKey> = listOf()): NavState =
    rememberSerializable(serializer = NavState.serializer()) {
        createNavState(
            start = start,
            pending = pending,
        )
    }
