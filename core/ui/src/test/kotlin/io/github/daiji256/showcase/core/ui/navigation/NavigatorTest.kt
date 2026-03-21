package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class NavigatorTest {
    @Serializable
    data class TestKey(val value: String) : NavKey

    @Test
    fun push_delegatesToRoot() {
        val state = NavState(root = NavNode(RootNavKey))
        val navigator = Navigator(state)
        val key = TestKey("1")

        assertTrue(navigator.push(key))
        assertEquals(key, state.root.currentChild!!.key)
    }

    @Test
    fun pop_delegatesToRoot() {
        val state = NavState(root = NavNode(RootNavKey))
        val navigator = Navigator(state)
        navigator.push(TestKey("1"))
        navigator.push(TestKey("2"))

        assertTrue(navigator.pop())
        assertNull(state.root.currentChild!!.currentChild)
    }

    @Test
    fun switch_delegatesToRoot() {
        val state = NavState(root = NavNode(RootNavKey))
        val navigator = Navigator(state)
        val key1 = TestKey("1")
        val key2 = TestKey("2")
        navigator.push(key1)

        assertTrue(navigator.switch(from = key1, to = key2))
        assertEquals(key2, state.root.currentChild!!.key)
    }

    @Test
    fun navigateUp_whenPopSucceeds_returnsTrue() {
        val state = NavState(root = NavNode(RootNavKey))
        val navigator = Navigator(state)
        navigator.push(TestKey("1"))
        navigator.push(TestKey("2"))

        assertTrue(navigator.navigateUp())
        assertNull(state.root.currentChild!!.currentChild)
    }

    @Test
    fun navigateUp_whenPopFailsAndPendingNotEmpty_navigatesToPending() {
        val pendingKey = TestKey("pending")
        val state = NavState(root = NavNode(RootNavKey), pending = mutableStateListOf(pendingKey))
        val navigator = Navigator(state)
        navigator.push(TestKey("1"))

        assertTrue(navigator.navigateUp())
        assertTrue(state.pending.isEmpty())
        assertEquals(pendingKey, state.root.currentChild!!.key)
    }

    @Test
    fun navigateUp_whenPopFailsAndPendingNotEmpty_reusesExistingNode() {
        val pendingKey = TestKey("pending")
        val state = NavState(root = NavNode(RootNavKey), pending = mutableStateListOf(pendingKey))
        val navigator = Navigator(state)

        navigator.push(pendingKey)
        val existingNode = state.root.currentChild
        navigator.switch(from = pendingKey, to = TestKey("1"))

        assertTrue(navigator.navigateUp())
        assertEquals(existingNode, state.root.currentChild)
    }

    @Test
    fun navigateUp_whenPopFailsAndPendingEmpty_returnsFalse() {
        val state = NavState(root = NavNode(RootNavKey))
        val navigator = Navigator(state)
        navigator.push(TestKey("1"))

        assertFalse(navigator.navigateUp())
    }

    @Test
    fun restart_initializesState() {
        val state = NavState(root = NavNode(RootNavKey))
        val navigator = Navigator(state)
        navigator.push(TestKey("1"))
        val startKey = TestKey("start")
        val pendingKeys = listOf<NavKey>(TestKey("p1"))

        navigator.restart(start = startKey, pending = pendingKeys)

        assertEquals(startKey, state.root.currentChild!!.key)
        assertEquals(pendingKeys, state.pending)
    }
}
