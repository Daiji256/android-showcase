package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.toMutableStateList
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.serialization.NavKeySerializer
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer

/**
 * Remember a [NavState].
 *
 * @param start the start [NavKey]
 * @param pending the pending keys to navigate up
 * @return a remembered [NavState]
 */
@Composable
fun rememberNavState(start: NavKey, pending: List<NavKey> = listOf()): NavState {
    val root = rememberSerializable(serializer = NavNode.serializer()) {
        NavNode(key = RootNavKey).also {
            val child = NavNode(key = start)
            it.currentChild = child
            it.children += child
        }
    }
    val pending = rememberSerializable(
        serializer = SnapshotStateListSerializer(elementSerializer = NavKeySerializer()),
    ) {
        pending.toMutableStateList()
    }
    return remember { NavState(root = root, pending = pending) }
}
