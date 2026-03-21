package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class NavNodeSerializerTest {
    @Serializable
    data object FirstNavKey : NavKey

    @Serializable
    data object SecondNavKey : NavKey

    @Serializable
    data object NavigationBarANavKey : NavKey

    @Serializable
    data object NavigationBarBNavKey : NavKey

    @Serializable
    data object NavigationBarBXNavKey : NavKey

    @Serializable
    data object NavigationBarBYNavKey : NavKey

    @Serializable
    data class OuterNavKey(val value: String) : NavKey

    @Test
    fun serializeAndDeserialize() {
        val root = NavNode(key = RootNavKey)
        root.push(key = FirstNavKey)
        root.push(key = SecondNavKey)
        root.push(key = NavigationBarANavKey)
        root.switch(from = setOf(NavigationBarANavKey), to = listOf(NavigationBarBNavKey))
        root.push(key = NavigationBarBXNavKey)
        root.switch(from = setOf(NavigationBarBXNavKey), to = listOf(NavigationBarBYNavKey))
        root.switch(from = setOf(NavigationBarBNavKey), to = listOf(NavigationBarANavKey))
        root.push(key = OuterNavKey(value = "a"))
        root.push(key = OuterNavKey(value = "b"))

        val encoded = Json.encodeToJsonElement(NavNode.serializer(), root)
        val decoded = Json.decodeFromJsonElement(NavNode.serializer(), encoded)
        assertEquals(
            encoded,
            Json.encodeToJsonElement(NavNode.serializer(), decoded),
        )
    }
}
