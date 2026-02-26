package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey

/**
 * Navigator for the [tree].
 *
 * @param T the type of the tree
 * @param tree the root navigation tree
 */
class Navigator<T : NavKey>(private val tree: NavNode<T>) {
    /**
     * navigate to the [route]
     *
     * @param route the destination [NavKey]
     * @return `true` if navigation was handled, `false` otherwise
     */
    fun navigate(route: T): Boolean = navigate(route = NavNode.Leaf(key = route))

    /**
     * navigate to the [route]
     *
     * @param route the destination [NavNode]
     * @return `true` if navigation was handled, `false` otherwise
     */
    fun navigate(route: NavNode<T>): Boolean = tree.navigate(route = route)

    /**
     * navigate to the [route]
     *
     * @param route the destination [NavKey]
     * @param popUpTo the destination to pop up to
     * @param inclusive whether the [popUpTo] destination should be popped
     * @return `true` if navigation was handled, `false` otherwise
     */
    fun navigate(route: T, popUpTo: T, inclusive: Boolean = false): Boolean =
        navigate(route = NavNode.Leaf(key = route), popUpTo = popUpTo, inclusive = inclusive)

    /**
     * navigate to the [route]
     *
     * @param route the destination [NavNode]
     * @param popUpTo the destination to pop up to
     * @param inclusive whether the [popUpTo] destination should be popped
     * @return `true` if navigation was handled, `false` otherwise
     */
    fun navigate(route: NavNode<T>, popUpTo: T, inclusive: Boolean = false): Boolean =
        tree.navigate(route = route, popUpTo = popUpTo, inclusive = inclusive)

    /**
     * navigate up
     *
     * @return `true` if navigation was handled, `false` otherwise
     */
    fun navigateUp(): Boolean = tree.navigateUp()

    /**
     * pop the back
     *
     * @return `true` if back navigation was handled, `false` otherwise
     */
    fun pop(): Boolean = tree.pop()

    /**
     * pop the back to the [route]
     *
     * @param route the destination [NavKey]
     * @param inclusive whether the [route] should be popped
     * @return `true` if back navigation was handled, `false` otherwise
     */
    fun pop(route: T, inclusive: Boolean): Boolean =
        tree.pop(route = route, inclusive = inclusive)
}
