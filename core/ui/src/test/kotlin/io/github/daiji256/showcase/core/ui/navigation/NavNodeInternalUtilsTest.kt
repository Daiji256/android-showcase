package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class NavNodeInternalUtilsTest {
    @Serializable
    data class TestKey(val value: String) : NavKey

    @Test
    fun push_newKey_returnsTrueAndAddsChild() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")

        assertTrue(root.push(key1))
        assertEquals(key1, root.currentChild!!.key)
        assertTrue(root.children.any { it.key == key1 })
    }

    @Test
    fun push_sameKey_returnsFalseAndDoesNotAddChild() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        root.push(key1)

        assertFalse(root.push(key1))
        assertEquals(key1, root.currentChild!!.key)
        assertTrue(root.children.any { it.key == key1 })
    }

    @Test
    fun push_delegatesToCurrentChild() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        val key2 = TestKey("2")
        root.push(key1)

        assertTrue(root.push(key2))
        assertEquals(key1, root.currentChild!!.key)
        assertEquals(key2, root.currentChild!!.currentChild!!.key)
    }

    @Test
    fun pop_noChild_returnsFalse() {
        val root = NavNode(RootNavKey)
        assertFalse(root.pop())
    }

    @Test
    fun pop_oneChild_returnsFalse() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        root.push(key1)
        assertFalse(root.pop())
        assertEquals(key1, root.currentChild!!.key)
    }

    @Test
    fun pop_delegatesToCurrentChild() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        val key2 = TestKey("2")
        root.push(key1)
        root.push(key2)

        assertTrue(root.pop())
        assertEquals(key1, root.currentChild!!.key)
        assertNull(root.currentChild!!.currentChild)
    }

    @Test
    fun switch_foundInFrom_createsNewChildIfToNotFound() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        val key2 = TestKey("2")
        root.push(key1)

        assertTrue(root.switch(from = setOf(key1), to = listOf(key2)))
        assertEquals(key2, root.currentChild!!.key)
        assertTrue(root.children.any { it.key == key1 })
        assertTrue(root.children.any { it.key == key2 })
    }

    @Test
    fun switch_foundInFrom_switchesToExistingChild() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        val key2 = TestKey("2")
        root.push(key1)
        root.switch(from = setOf(key1), to = listOf(key2))

        assertTrue(root.switch(from = setOf(key2), to = listOf(key1)))
        assertEquals(key1, root.currentChild!!.key)
        assertTrue(root.children.any { it.key == key1 })
        assertTrue(root.children.any { it.key == key2 })
    }

    @Test
    fun switch_notFoundInFrom_delegatesToChild() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        val key2 = TestKey("2")
        val key3 = TestKey("3")
        root.push(key1)
        root.push(key2)

        assertTrue(root.switch(from = setOf(key2), to = listOf(key3)))
        assertEquals(key1, root.currentChild!!.key)
        assertEquals(key3, root.currentChild!!.currentChild!!.key)
        assertTrue(root.currentChild!!.children.any { it.key == key2 })
    }

    @Test
    fun switch_emptyFrom_returnsFalse() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        assertFalse(root.switch(from = setOf(), to = listOf(key1)))
    }

    @Test
    fun switch_emptyTo_returnsFalse() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        root.push(key1)
        assertFalse(root.switch(from = setOf(key1), to = listOf()))
    }

    @Test
    fun switch_notFoundInFromAndNoChild_returnsFalse() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        val key2 = TestKey("2")
        assertFalse(root.switch(from = setOf(key1), to = listOf(key2)))
    }

    @Test
    fun switch_toWithMultipleKeys_picksFirstExisting() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        val key2 = TestKey("2")
        val key3 = TestKey("3")
        root.push(key1)
        root.switch(from = setOf(key1), to = listOf(key2))
        root.switch(from = setOf(key2), to = listOf(key3))

        assertTrue(root.switch(from = setOf(key3), to = listOf(key1, key2)))
        assertEquals(key1, root.currentChild!!.key)
    }

    @Test
    fun switch_toWithMultipleKeys_createsFirstIfNoneExist() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        val key2 = TestKey("2")
        val key3 = TestKey("3")
        root.push(key1)

        assertTrue(root.switch(from = setOf(key1), to = listOf(key2, key3)))
        assertEquals(key2, root.currentChild!!.key)
        assertTrue(root.children.any { it.key == key2 })
        assertFalse(root.children.any { it.key == key3 })
    }

    @Test
    fun switch_fromWithMultipleKeys_switchesIfAnyMatches() {
        val root = NavNode(RootNavKey)
        val key1 = TestKey("1")
        val key2 = TestKey("2")
        val key3 = TestKey("3")
        root.push(key1)

        assertTrue(root.switch(from = setOf(key3, key1), to = listOf(key2)))
        assertEquals(key2, root.currentChild!!.key)
    }
}
