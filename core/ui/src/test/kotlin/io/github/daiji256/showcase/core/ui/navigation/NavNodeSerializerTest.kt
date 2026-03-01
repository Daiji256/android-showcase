package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.serialization.NavKeySerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class NavNodeSerializerTest {
    @Serializable
    data object RootNavKey : NavKey

    @Serializable
    data object FirstNavKey : NavKey

    @Serializable
    data object SecondNavKey : NavKey

    @Serializable
    data object NavigationBarRootNavKey : NavKey

    @Serializable
    data object NavigationBarARootNavKey : NavKey

    @Serializable
    data object NavigationBarANavKey : NavKey

    @Serializable
    data object NavigationBarBRootNavKey : NavKey

    @Serializable
    data object NavigationBarBNavKey : NavKey

    @Serializable
    data object NavigationBarBSwitchRootNavKey : NavKey

    @Serializable
    data object NavigationBarBSwitchXNavKey : NavKey

    @Serializable
    data object NavigationBarBSwitchYNavKey : NavKey

    @Serializable
    data class OuterNavKey(val value: String) : NavKey

    @Test
    fun serializeAndDeserialize() {
        val original: NavNode<NavKey> = NavNode.Stack(
            key = RootNavKey,
            children = listOf(
                NavNode.Leaf(
                    key = SecondNavKey,
                    up = NavNode.Leaf(key = FirstNavKey),
                ),
                NavNode.Select(
                    key = NavigationBarRootNavKey,
                    selected = NavigationBarARootNavKey,
                    children = setOf(
                        NavNode.Stack(
                            key = NavigationBarARootNavKey,
                            children = listOf(
                                NavNode.Leaf(key = NavigationBarANavKey),
                                NavNode.Leaf(key = OuterNavKey(value = "a")),
                                NavNode.Leaf(key = OuterNavKey(value = "b")),
                            ),
                        ),
                        NavNode.Stack(
                            key = NavigationBarBRootNavKey,
                            children = listOf(
                                NavNode.Leaf(key = NavigationBarBNavKey),
                                NavNode.Select(
                                    key = NavigationBarBSwitchRootNavKey,
                                    selected = NavigationBarBSwitchYNavKey,
                                    children = setOf(
                                        NavNode.Leaf(key = NavigationBarBSwitchXNavKey),
                                        NavNode.Leaf(key = NavigationBarBSwitchYNavKey),
                                    ),
                                ),
                            ),
                        ),
                    ),
                ),
            ),
        )

        val serializer = NavNode.serializer(NavKeySerializer())
        val encoded = Json.encodeToJsonElement(serializer, original)
        val decoded = Json.decodeFromJsonElement(serializer, encoded)
        assertEquals(
            encoded,
            Json.encodeToJsonElement(serializer, decoded),
        )
    }
}
