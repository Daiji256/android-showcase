package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals

class NavNodeKeysTest {
    @Serializable
    data class TestKey(val value: String) : NavKey

    @Test
    fun fromRoot_emptyRoot_returnsEmptyLists() {
        val root = NavNode(RootNavKey)
        val keys = NavNodeKeys.fromRoot(root)
        assertEquals(listOf(), keys.active)
        assertEquals(listOf(), keys.inactive)
        assertEquals(listOf(), keys.all)
    }

    @Test
    fun fromRoot_singleActiveChild() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        root.push(key1)
        val keys = NavNodeKeys.fromRoot(root)
        assertEquals(listOf(key1), keys.active)
        assertEquals(listOf(), keys.inactive)
        assertEquals(listOf(key1), keys.all)
    }

    @Test
    fun fromRoot_activeAndInactiveSiblings() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        val key2 = TestKey("2")
        root.push(key1)
        root.switch(from = setOf(key1), to = listOf(key2))
        val keys = NavNodeKeys.fromRoot(root)
        assertEquals(listOf(key2), keys.active)
        assertEquals(listOf(key1), keys.inactive)
        assertEquals(listOf(key1, key2), keys.all)
    }

    @Test
    fun fromRoot_deepHierarchy() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        val key1A = TestKey("1-a")
        val key2 = TestKey("2")
        root.push(key1)
        root.push(key1A)
        root.switch(from = setOf(key1), to = listOf(key2))
        val keys = NavNodeKeys.fromRoot(root)

        assertEquals(listOf(key2), keys.active)
        assertEquals(listOf(key1, key1A), keys.inactive)
        assertEquals(listOf(key1, key1A, key2), keys.all)
    }

    @Test
    fun fromRoot_multipleActiveNodesInPath() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        val key1A = TestKey("1-A")
        root.push(key1)
        root.push(key1A)
        val keys = NavNodeKeys.fromRoot(root)
        assertEquals(listOf(key1, key1A), keys.active)
        assertEquals(listOf(), keys.inactive)
        assertEquals(listOf(key1, key1A), keys.all)
    }
}
