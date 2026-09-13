package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class NavStateSerializerTest {
    @Serializable
    data object FirstNavKey : NavKey

    @Serializable
    data object SecondNavKey : NavKey

    @Serializable
    data object PendingNavKey : NavKey

    @Test
    fun serializeAndDeserialize() {
        val state = NavState(
            root = NavNode(key = RootNavKey).also {
                val child = NavNode(key = FirstNavKey)
                it.currentChild = child
                it.children += child
            },
            pending = mutableStateListOf(PendingNavKey),
        )

        val encoded = Json.encodeToJsonElement(NavState.serializer(), state)
        val decoded = Json.decodeFromJsonElement(NavState.serializer(), encoded)
        assertEquals(
            encoded,
            Json.encodeToJsonElement(NavState.serializer(), decoded),
        )
    }
}
