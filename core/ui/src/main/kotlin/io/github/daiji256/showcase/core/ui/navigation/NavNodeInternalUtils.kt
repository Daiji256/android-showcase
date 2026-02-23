package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey

internal val <T : NavKey> NavNode.Stack<T>.currentChild: NavNode<T>
    get() = children.lastOrNull() ?: error("No children")

internal val <T : NavKey> NavNode.Select<T>.selectedChild
    get() = children.firstOrNull { it.key == selected } ?: error("No child for $selected")
