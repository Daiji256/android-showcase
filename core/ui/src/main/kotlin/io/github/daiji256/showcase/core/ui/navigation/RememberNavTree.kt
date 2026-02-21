package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.serialization.NavKeySerializer

/**
 * Remember a [NavNode].
 *
 * @param T the type of the tree
 * @param init the initializer for the tree
 * @return a remembered [NavNode]
 */
@Composable
fun <T : NavKey> rememberNavTree(init: () -> NavNode<T>): NavNode<T> =
    rememberSerializable(
        serializer = NavNode.serializer(NavKeySerializer()),
        init = init,
    )
