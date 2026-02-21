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
        val tree = NavNode.Stack(
            key = TestNavKey(value = "stack"),
            children = listOf(NavNode.Leaf(key = a)),
        )

        assertTrue(tree.navigate(route = NavNode.Leaf(key = b)))
        assertEquals(2, tree.children.size)
        assertEquals(b, (tree.children.last() as NavNode.Leaf).key)
    }

    @Test
    fun stack_back_single() {
        val a = TestNavKey(value = "a")
        val tree = NavNode.Stack(
            key = TestNavKey(value = "stack"),
            children = listOf(NavNode.Leaf(key = a)),
        )

        assertFalse(tree.back())
        assertEquals(1, tree.children.size)
        assertEquals(a, (tree.children.last() as NavNode.Leaf).key)
    }

    @Test
    fun stack_back_multiple() {
        val a = TestNavKey(value = "a")
        val b = TestNavKey(value = "b")
        val tree = NavNode.Stack(
            key = TestNavKey(value = "stack"),
            children = listOf(NavNode.Leaf(key = a), NavNode.Leaf(key = b)),
        )

        assertTrue(tree.back())
        assertEquals(1, tree.children.size)
        assertEquals(a, (tree.children.last() as NavNode.Leaf).key)
    }

    @Test
    fun select_navigate_switch() {
        val a = TestNavKey(value = "a")
        val b = TestNavKey(value = "b")
        val x = TestNavKey(value = "x")
        val y = TestNavKey(value = "y")
        val tree = NavNode.Select(
            key = TestNavKey(value = "select"),
            selected = a,
            children = mapOf(a to NavNode.Leaf(key = x), b to NavNode.Leaf(key = y)),
        )

        assertTrue(tree.navigate(route = NavNode.Leaf(key = b)))
        assertEquals(b, tree.selected)
        assertEquals(y, (tree.selectedChild as NavNode.Leaf).key)
    }

    @Test
    fun select_navigate_no_switch() {
        val a = TestNavKey(value = "a")
        val b = TestNavKey(value = "b")
        val c = TestNavKey(value = "c")
        val x = TestNavKey(value = "x")
        val y = TestNavKey(value = "y")
        val tree = NavNode.Select(
            key = TestNavKey(value = "select"),
            selected = a,
            children = mapOf(
                a to NavNode.Stack(key = a, children = listOf(NavNode.Leaf(key = x))),
                b to NavNode.Stack(key = b, children = listOf(NavNode.Leaf(key = y))),
            ),
        )

        assertTrue(tree.navigate(route = NavNode.Leaf(key = c)))
        assertEquals(a, tree.selected)
        assertEquals(2, (tree.selectedChild as NavNode.Stack).children.size)
        assertEquals(c, ((tree.selectedChild as NavNode.Stack).children.last() as NavNode.Leaf).key)
        assertEquals(1, (tree.children.getValue(b) as NavNode.Stack).children.size)
        assertTrue(tree.back())
        assertEquals(a, tree.selected)
        assertEquals(1, (tree.selectedChild as NavNode.Stack).children.size)
        assertFalse(tree.back())
    }
}
