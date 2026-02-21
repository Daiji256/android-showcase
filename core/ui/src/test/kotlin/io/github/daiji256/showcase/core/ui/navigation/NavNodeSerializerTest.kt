package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.serialization.NavKeySerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class NavNodeSerializerTest {
    @Test
    fun serialize_and_deserialize() {
        val original: NavNode<RootNavKey> = NavNode.Stack(
            children = listOf(
                NavNode.Leaf(key = FirstNavKey),
                NavNode.Select(
                    selected = Tab1NavKey,
                    children = mapOf(
                        Pair(
                            Tab1NavKey,
                            NavNode.Stack(
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
                                children = listOf(
                                    NavNode.Leaf(key = Tab2NavKey),
                                    NavNode.Select(
                                        selected = Tab2SwitchBNavKey,
                                        children = mapOf(
                                            Pair(
                                                Tab2SwitchANavKey,
                                                NavNode.Stack(
                                                    children = listOf(
                                                        NavNode.Leaf(key = Tab2SwitchANavKey),
                                                    ),
                                                ),
                                            ),
                                            Pair(
                                                Tab2SwitchBNavKey,
                                                NavNode.Stack(
                                                    children = listOf(
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
                ),
            ),
        )

        val serializer = NavNode.serializer(NavKeySerializer<RootNavKey>())
        val encoded = Json.encodeToJsonElement(serializer, original)
        val decoded = Json.decodeFromJsonElement(serializer, encoded)
        assertEquals(
            encoded,
            Json.encodeToJsonElement(serializer, decoded),
        )
    }

    interface RootNavKey : NavKey

    @Serializable
    data object FirstNavKey : RootNavKey

    interface TabNavKey : RootNavKey

    @Serializable
    data object Tab1NavKey : TabNavKey

    @Serializable
    data object Tab2NavKey : TabNavKey

    interface Tab2SwitchNavKey : TabNavKey

    @Serializable
    data object Tab2SwitchANavKey : Tab2SwitchNavKey

    @Serializable
    data object Tab2SwitchBNavKey : Tab2SwitchNavKey

    @Serializable
    data class OuterNavKey(val value: String) : RootNavKey
}
