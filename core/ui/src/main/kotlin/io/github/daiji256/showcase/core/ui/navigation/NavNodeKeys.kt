package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey

internal data class NavNodeKeys(
    val active: List<NavKey>,
    val inactive: List<NavKey>,
) {
    /**
     * combined list to remember the state of all keys
     */
    val all: List<NavKey> = inactive + active

    companion object {
        fun fromRoot(root: NavNode): NavNodeKeys {
            val active = mutableListOf<NavKey>()
            val inactive = mutableListOf<NavKey>()

            // stack of (isActive, node)
            val pending = ArrayDeque<Pair<Boolean, NavNode>>()
            pending.addLast(true to root)

            while (pending.isNotEmpty()) {
                val (isActive, node) = pending.removeFirst()
                if (node.key != RootNavKey) {
                    (if (isActive) active else inactive) += node.key
                }
                node.children.forEach {
                    pending.addLast((isActive && it == node.currentChild) to it)
                }
            }

            return NavNodeKeys(active = active, inactive = inactive)
        }
    }
}
