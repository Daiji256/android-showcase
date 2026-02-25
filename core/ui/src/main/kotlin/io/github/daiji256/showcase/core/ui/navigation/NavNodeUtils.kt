package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey

/**
 * navigate to the [route]
 *
 * @param route the destination [NavNode]
 * @return `true` if navigation was handled, `false` otherwise
 */
fun <T : NavKey> NavNode<T>.navigate(route: NavNode<T>): Boolean {
    // if same key, treat as success (single top)
    if (key == route.key) return true

    when (this) {
        is NavNode.Leaf -> {
            // cannot navigate from leaf node
            return false
        }

        is NavNode.Stack -> {
            // delegate navigation to current child
            if (currentChild.navigate(route = route)) return true

            // push route
            return children.add(route)
        }

        is NavNode.Select -> {
            // if already selected, treat as success (single top)
            if (selected == route.key) return true

            // if route is one of the children, switch selection
            if (children.any { it.key == route.key }) {
                selected = route.key
                return true
            }

            // delegate to selected child
            return selectedChild.navigate(route = route)
        }
    }
}

/**
 * navigate to the [route]
 *
 * @param route the destination [NavNode]
 * @param popUpTo the destination to pop up to
 * @param inclusive whether the [popUpTo] destination should be popped
 * @return `true` if navigation was handled, `false` otherwise
 */
fun <T : NavKey> NavNode<T>.navigate(
    route: NavNode<T>,
    popUpTo: T,
    inclusive: Boolean = false,
): Boolean {
    // if same key, treat as success (single top)
    if (key == route.key) return true

    when (this) {
        is NavNode.Leaf -> {
            // cannot navigate from leaf node
            return false
        }

        is NavNode.Stack -> {
            // if same key, pop and treat as success (single top)
            if (currentChild.key == route.key) {
                val index = children.indexOfLast { it.key == popUpTo }
                if (index != -1) {
                    children.removeRange(index + if (inclusive) 0 else 1, children.size - 1)
                }
                return true
            }

            // delegate navigation to current child
            if (currentChild.navigate(route, popUpTo, inclusive)) return true

            // pop and then push route
            val index = children.indexOfLast { it.key == popUpTo }
            if (index != -1) {
                children.removeRange(index + if (inclusive) 0 else 1, children.size)
            }
            return children.add(route)
        }

        is NavNode.Select -> {
            // if already selected, treat as success (single top)
            if (selected == route.key) return true

            // if route is one of the children, switch selection
            if (children.any { it.key == route.key }) {
                selected = route.key
                return true
            }

            // delegate to the selected child
            return selectedChild.navigate(route, popUpTo, inclusive)
        }
    }
}

/**
 * navigate up
 *
 * @return `true` if navigation was handled, `false` otherwise
 */
fun <T : NavKey> NavNode<T>.navigateUp(): Boolean {
    when (this) {
        is NavNode.Leaf -> {
            // cannot navigate from leaf node
            return false
        }

        is NavNode.Stack -> {
            // delegate navigation to current child
            if (currentChild.navigateUp()) return true

            // pop and navigate up
            val up = currentChild.up
            if (up != null) {
                children.removeAt(children.lastIndex)

                // if there are children, delegate to the last child
                if (children.lastOrNull()?.navigate(route = up) == true) return true

                // push up
                return children.add(up)
            }

            // if it will be empty, don't pop
            if (children.size <= 1) return false

            // pop the current child
            children.removeAt(children.lastIndex)
            return true
        }

        is NavNode.Select -> {
            // delegate to the selected child
            return selectedChild.navigateUp()
        }
    }
}

/**
 * pop the back
 *
 * @return `true` if back navigation was handled, `false` otherwise
 */
fun <T : NavKey> NavNode<T>.pop(): Boolean {
    when (this) {
        is NavNode.Leaf -> {
            // cannot pop from leaf node
            return false
        }

        is NavNode.Stack -> {
            // delegate pop to current child
            if (currentChild.pop()) return true

            // if it will be empty, don't pop
            if (children.size <= 1) return false

            // pop the current child
            children.removeAt(children.lastIndex)
            return true
        }

        is NavNode.Select -> {
            // delegate to the selected child
            return selectedChild.pop()
        }
    }
}

/**
 * pop the back to the [route]
 *
 * @param route the destination [NavNode]
 * @param inclusive whether the [route] should be popped
 * @return `true` if back navigation was handled, `false` otherwise
 */
fun <T : NavKey> NavNode<T>.pop(route: T, inclusive: Boolean): Boolean {
    when (this) {
        is NavNode.Leaf -> {
            // cannot pop from leaf node
            return false
        }

        is NavNode.Stack -> {
            // delegate pop to current child
            if (currentChild.pop(route = route, inclusive = inclusive)) return true

            // find index of the route
            val index = children.indexOfLast { it.key == route }

            // if not found, don't pop
            if (index == -1) return false

            // if it will be empty, don't pop
            if (index == 0 && inclusive) return false

            // pop up to the route
            children.removeRange(index + if (inclusive) 0 else 1, children.size)
            return true
        }

        is NavNode.Select -> {
            // delegate to the selected child
            return selectedChild.pop(route = route, inclusive = inclusive)
        }
    }
}

internal val <T : NavKey> NavNode.Stack<T>.currentChild: NavNode<T>
    get() = children.lastOrNull() ?: error("No children")

internal val <T : NavKey> NavNode.Select<T>.selectedChild
    get() = children.firstOrNull { it.key == selected } ?: error("No child for $selected")
