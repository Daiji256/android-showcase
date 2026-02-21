package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NavNodeTest {
    @Serializable
    data class TestNavKey(val value: String) : NavKey

    @Test
    fun stack_navigate_add_route() {
        val a = TestNavKey(value = "a")
        val b = TestNavKey(value = "b")
        val tree = NavNode.Stack(children = listOf(NavNode.Key(key = a)))

        assertTrue(tree.navigate(route = NavNode.Key(key = b)))
        assertEquals(2, tree.children.size)
        assertEquals(b, (tree.children.last() as NavNode.Key).key)
    }

    @Test
    fun stack_back_single() {
        val a = TestNavKey(value = "a")
        val tree = NavNode.Stack(children = listOf(NavNode.Key(key = a)))

        assertFalse(tree.back())
        assertEquals(1, tree.children.size)
        assertEquals(a, (tree.children.last() as NavNode.Key).key)
    }

    @Test
    fun stack_back_multiple() {
        val a = TestNavKey(value = "a")
        val b = TestNavKey(value = "b")
        val tree = NavNode.Stack(
            children = listOf(NavNode.Key(key = a), NavNode.Key(key = b)),
        )

        assertTrue(tree.back())
        assertEquals(1, tree.children.size)
        assertEquals(a, (tree.children.last() as NavNode.Key).key)
    }

    @Test
    fun select_navigate_switch() {
        val a = TestNavKey(value = "a")
        val b = TestNavKey(value = "b")
        val tree = NavNode.Select(selected = a, children = setOf(a, b))

        assertTrue(tree.navigate(route = NavNode.Key(key = b)))
        assertEquals(b, tree.selected)
        assertEquals(1, tree.selectedChild.children.size)
        assertEquals(b, (tree.selectedChild.children.last() as NavNode.Key).key)
    }

    @Test
    fun select_navigate_no_switch() {
        val a = TestNavKey(value = "a")
        val b = TestNavKey(value = "b")
        val c = TestNavKey(value = "c")
        val tree = NavNode.Select(selected = a, children = setOf(a, b))

        assertTrue(tree.navigate(route = NavNode.Key(key = c)))
        assertEquals(a, tree.selected)
        assertEquals(2, tree.selectedChild.children.size)
        assertEquals(c, (tree.selectedChild.children.last() as NavNode.Key).key)
        assertEquals(1, tree.children.getValue(b).children.size)
        assertTrue(tree.back())
        assertEquals(a, tree.selected)
        assertEquals(1, tree.selectedChild.children.size)
        assertFalse(tree.back())
    }
}
