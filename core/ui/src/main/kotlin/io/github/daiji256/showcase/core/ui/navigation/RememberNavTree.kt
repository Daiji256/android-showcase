package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey

/**
 * Remembers a navigation tree ([NavNode.Stack]) for Compose.
 *
 * @param T the type of the tree
 * @param initial the initial children of the tree
 * @return a remembered [NavNode.Stack]
 */
@Composable
fun <T : NavKey> rememberNavTree(
    vararg initial: NavNode<T>,
): NavNode.Stack<T> =
    // TODO: Saveable
    remember {
        NavNode.Stack(children = initial.toList())
    }
