package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Represents a node in a navigation tree.
 *
 * @param T the type of the tree
 */
@Serializable
sealed interface NavNode<T : NavKey> {
    /**
     * the associated [NavKey]
     */
    val key: T

    /**
     * navigate to the [route]
     *
     * @param route the destination [NavNode]
     * @return `true` if navigation was handled, `false` otherwise
     */
    fun navigate(route: NavNode<T>): Boolean

    /**
     * navigate back
     *
     * @return `true` if back navigation was handled, `false` otherwise
     */
    fun back(): Boolean

    /**
     * Leaf node that represent navigation key.
     */
    @Serializable(with = NavNodeLeafSerializer::class)
    class Leaf<T : NavKey>(
        override val key: T,
    ) : NavNode<T> {
        override fun navigate(route: NavNode<T>) = false

        override fun back(): Boolean = false
    }

    /**
     * Stack node that manages a list of child nodes.
     *
     * @param children the list of child nodes
     */
    @Serializable(with = NavNodeStackSerializer::class)
    class Stack<T : NavKey>(
        override val key: T,
        children: List<NavNode<T>>,
    ) : NavNode<T> {
        init {
            require(children.isNotEmpty()) {
                "Stack must have at least one child"
            }
        }

        private val _children = children.toMutableStateList()

        /**
         * the list of child nodes
         */
        val children: List<NavNode<T>>
            get() = _children

        private val currentChild
            get() = children.lastOrNull() ?: error("No children")

        override fun navigate(route: NavNode<T>): Boolean =
            currentChild.navigate(route) || _children.add(route)

        override fun back(): Boolean {
            if (currentChild.back()) return true
            if (children.size <= 1) return false
            _children.removeAt(children.lastIndex)
            return true
        }
    }

    /**
     * Select node that manages a selected child node.
     *
     * @param selected the selected key
     * @property children the set of child nodes
     */
    @Serializable(with = NavNodeSelectSerializer::class)
    class Select<T : NavKey>(
        override val key: T,
        selected: T,
        val children: Set<NavNode<T>>,
    ) : NavNode<T> {
        init {
            require(children.isNotEmpty()) {
                "Select must have at least one child"
            }
            require(children.any { it.key == selected }) {
                "Selected key must be in children"
            }
            require(children.size == children.distinctBy { it.key }.size) {
                "Children must have unique keys"
            }
        }

        /**
         * the selected key
         */
        var selected by mutableStateOf(selected)
            private set

        private val selectedChild
            get() = children.firstOrNull { it.key == selected }
                ?: error("No child for ${this@Select.selected}")

        override fun navigate(route: NavNode<T>): Boolean {
            if (children.any { it.key == route.key }) {
                selected = route.key
                return true
            }
            return selectedChild.navigate(route)
        }

        override fun back(): Boolean = selectedChild.back()
    }
}
