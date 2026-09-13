package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Represents the state of the navigation.
 *
 * @property root the root [NavNode]
 * @property pending the pending keys to navigate up
 */
@Serializable(with = NavStateSerializer::class)
class NavState internal constructor(
    val root: NavNode,
    val pending: SnapshotStateList<NavKey> = mutableStateListOf(),
) {
    init {
        require(root.key == RootNavKey) {
            "Root NavNode must have key RootNavKey"
        }
    }
}

/**
 * Creates a [NavState] with the given start key and pending keys.
 *
 * @param start the start [NavKey]
 * @param pending the pending keys to navigate up
 * @return a [NavState] initialized with the given start key and pending keys
 */
@InternalNavStateApi
fun createNavState(start: NavKey, pending: List<NavKey>): NavState =
    NavState(
        root = NavNode(key = RootNavKey).also {
            val child = NavNode(key = start)
            it.currentChild = child
            it.children += child
        },
        pending = pending.toMutableStateList(),
    )
