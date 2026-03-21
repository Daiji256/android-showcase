package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateSet
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Represents a navigation node.
 *
 * @property key the associated [NavKey]
 */
@Serializable(with = NavNodeSerializer::class)
class NavNode internal constructor(
    val key: NavKey,
) {
    /**
     * the current child [NavNode]; `null` if no child
     */
    var currentChild: NavNode? by mutableStateOf<NavNode?>(null)

    /**
     * the children [NavNode]s; empty if no children
     */
    val children: SnapshotStateSet<NavNode> = mutableStateSetOf<NavNode>()
}
