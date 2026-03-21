package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey

/**
 * Represents the state of the navigation.
 *
 * @property root the root [NavNode]
 * @property pending the pending keys to navigate up
 */
class NavState(
    val root: NavNode,
    val pending: SnapshotStateList<NavKey> = mutableStateListOf(),
) {
    init {
        require(root.key == RootNavKey) {
            "Root NavNode must have key RootNavKey"
        }
    }
}
