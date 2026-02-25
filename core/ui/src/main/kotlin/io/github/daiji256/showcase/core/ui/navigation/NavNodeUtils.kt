package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey

/**
 * navigate to the [route]
 *
 * @param route the destination [NavNode]
 * @return `true` if navigation was handled, `false` otherwise
 */
fun <T : NavKey> NavNode<T>.navigate(route: NavNode<T>): Boolean {
    if (key == route.key) return true
    when (this) {
        is NavNode.Leaf -> {
            return false
        }

        is NavNode.Stack -> {
            if (currentChild.navigate(route = route)) return true
            return children.add(route)
        }

        is NavNode.Select -> {
            if (selected == route.key) return true
            if (children.any { it.key == route.key }) {
                selected = route.key
                return true
            }
            return selectedChild.navigate(route = route)
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
            return false
        }

        is NavNode.Stack -> {
            if (currentChild.navigateUp()) return true
            currentChild.up?.let { up ->
                children.removeAt(children.lastIndex)
                if (children.lastOrNull()?.navigate(route = up) == true) return true
                return children.add(up)
            }
            if (children.size <= 1) return false
            children.removeAt(children.lastIndex)
            return true
        }

        is NavNode.Select -> {
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
            return false
        }

        is NavNode.Stack -> {
            if (currentChild.pop()) return true
            if (children.size <= 1) return false
            children.removeAt(children.lastIndex)
            return true
        }

        is NavNode.Select -> {
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
            return false
        }

        is NavNode.Stack -> {
            if (currentChild.pop(route = route, inclusive = inclusive)) return true
            if (children.size <= 1) return false
            val index = children.indexOfLast { it.key == route }
            if (index == -1) return false
            children.removeRange(index + if (inclusive) 0 else 1, children.size)
            return true
        }

        is NavNode.Select -> {
            return selectedChild.pop(route = route, inclusive = inclusive)
        }
    }
}

internal val <T : NavKey> NavNode.Stack<T>.currentChild: NavNode<T>
    get() = children.lastOrNull() ?: error("No children")

internal val <T : NavKey> NavNode.Select<T>.selectedChild
    get() = children.firstOrNull { it.key == selected } ?: error("No child for $selected")
