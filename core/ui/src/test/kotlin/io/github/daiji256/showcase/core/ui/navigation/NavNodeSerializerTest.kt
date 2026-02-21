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
    data object TabSelectNavKey : NavKey

    @Serializable
    data object Tab1StackNavKey : NavKey

    @Serializable
    data object Tab1NavKey : NavKey

    @Serializable
    data object Tab2StackNavKey : NavKey

    @Serializable
    data object Tab2NavKey : NavKey

    @Serializable
    data object Tab2SwitchNavKey : NavKey

    @Serializable
    data object Tab2SwitchANavKey : NavKey

    @Serializable
    data object Tab2SwitchBNavKey : NavKey

    @Serializable
    data class OuterNavKey(val value: String) : NavKey

    @Test
    fun serialize_and_deserialize() {
        val original: NavNode<NavKey> = NavNode.Stack(
            key = RootNavKey,
            children = listOf(
                NavNode.Leaf(key = FirstNavKey),
                NavNode.Select(
                    key = TabSelectNavKey,
                    selected = Tab1NavKey,
                    children = mapOf(
                        Pair(
                            Tab1NavKey,
                            NavNode.Stack(
                                key = Tab1StackNavKey,
                                children = listOf(
                                    NavNode.Leaf(key = Tab1NavKey),
                                    NavNode.Leaf(key = OuterNavKey(value = "a")),
                                    NavNode.Leaf(key = OuterNavKey(value = "b")),
                                ),
                            ),
                        ),
                        Pair(
                            Tab2NavKey,
                            NavNode.Stack(
                                key = Tab2StackNavKey,
                                children = listOf(
                                    NavNode.Leaf(key = Tab2NavKey),
                                    NavNode.Select(
                                        key = Tab2SwitchNavKey,
                                        selected = Tab2SwitchBNavKey,
                                        children = mapOf(
                                            Pair(
                                                Tab2SwitchANavKey,
                                                NavNode.Leaf(key = Tab2SwitchANavKey),
                                            ),
                                            Pair(
                                                Tab2SwitchBNavKey,
                                                NavNode.Leaf(key = Tab2SwitchBNavKey),
                                            ),
                                        ),
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
