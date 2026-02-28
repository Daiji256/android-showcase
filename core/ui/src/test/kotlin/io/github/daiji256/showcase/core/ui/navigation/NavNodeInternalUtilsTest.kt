package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NavNodeInternalUtilsTest {
    @Serializable
    data class TestKey(val value: String) : NavKey

    @Test
    fun leaf_navigate_returnsFalse() {
        val route = NavNode.Leaf(TestKey("route"))
        val tree = NavNode.Leaf(TestKey("leaf"))
        assertFalse(tree.navigate(route))
    }

    @Test
    fun stack_navigate_pushesRoute() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val route = NavNode.Leaf(TestKey("route"))
        val tree = NavNode.Stack(TestKey("stack"), listOf(child1))

        assertTrue(tree.navigate(route))
        assertEquals(2, tree.children.size)
        assertEquals(route.key, tree.children.last().key)
    }

    @Test
    fun stack_navigate_sameKey_returnsTrue() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val route = NavNode.Stack(TestKey("stack"), listOf(child1))
        val tree = NavNode.Stack(TestKey("stack"), listOf(NavNode.Leaf(TestKey("child1"))))

        assertTrue(tree.navigate(route))
        assertEquals(1, tree.children.size)
        assertEquals(child1.key, tree.children.last().key)
    }

    @Test
    fun stack_navigate_delegatesToLastChild() {
        val grandchild1 = NavNode.Leaf(TestKey("grandchild1"))
        val childStack = NavNode.Stack(TestKey("childStack"), listOf(grandchild1))
        val route = NavNode.Leaf(TestKey("route"))
        val tree = NavNode.Stack(TestKey("rootStack"), listOf(childStack))

        assertTrue(tree.navigate(route))
        assertEquals(1, tree.children.size)
        assertEquals(2, childStack.children.size)
        assertEquals(route.key, childStack.children.last().key)
    }

    @Test
    fun select_navigate_switchesSelection() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val child2 = NavNode.Leaf(TestKey("child2"))
        val tree = NavNode.Select(TestKey("select"), child1.key, setOf(child1, child2))

        assertTrue(tree.navigate(child2))
        assertEquals(child2.key, tree.selected)
    }

    @Test
    fun select_navigate_alreadySelected_returnsTrue() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val route = NavNode.Leaf(TestKey("child1"))
        val tree = NavNode.Select(TestKey("select"), child1.key, setOf(child1))

        assertTrue(tree.navigate(route))
        assertEquals(child1.key, tree.selected)
    }

    @Test
    fun select_navigate_delegatesToSelectedChild() {
        val grandchild1 = NavNode.Leaf(TestKey("grandchild1"))
        val childStack = NavNode.Stack(TestKey("childStack"), listOf(grandchild1))
        val route = NavNode.Leaf(TestKey("route"))
        val tree = NavNode.Select(TestKey("select"), childStack.key, setOf(childStack))

        assertTrue(tree.navigate(route))
        assertEquals(2, childStack.children.size)
        assertEquals(route.key, childStack.children.last().key)
    }

    @Test
    fun stack_navigate_withPopUpTo() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val child2 = NavNode.Leaf(TestKey("child2"))
        val route = NavNode.Leaf(TestKey("route"))
        val tree = NavNode.Stack(TestKey("stack"), listOf(child1, child2))

        assertTrue(tree.navigate(route, popUpTo = child1.key, inclusive = false))
        assertEquals(2, tree.children.size)
        assertEquals(child1.key, tree.children[0].key)
        assertEquals(route.key, tree.children[1].key)
    }

    @Test
    fun stack_navigate_withPopUpTo_inclusive() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val child2 = NavNode.Leaf(TestKey("child2"))
        val route = NavNode.Leaf(TestKey("route"))
        val tree = NavNode.Stack(TestKey("stack"), listOf(child1, child2))

        assertTrue(tree.navigate(route, popUpTo = child1.key, inclusive = true))
        assertEquals(1, tree.children.size)
        assertEquals(route.key, tree.children[0].key)
    }

    @Test
    fun stack_navigateUp_removesLastChild() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val child2 = NavNode.Leaf(TestKey("child2"))
        val tree = NavNode.Stack(TestKey("stack"), listOf(child1, child2))

        assertTrue(tree.navigateUp())
        assertEquals(1, tree.children.size)
        assertEquals(child1.key, tree.children[0].key)
    }

    @Test
    fun stack_navigateUp_sizeOne_returnsFalse() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val tree = NavNode.Stack(TestKey("stack"), listOf(child1))

        assertFalse(tree.navigateUp())
        assertEquals(1, tree.children.size)
        assertEquals(child1.key, tree.children.last().key)
    }

    @Test
    fun stack_navigateUp_withUpNode() {
        val upNode = NavNode.Leaf(TestKey("up"))
        val child1 = NavNode.Leaf(TestKey("child1"))
        val child2 = NavNode.Leaf(TestKey("child2"), up = upNode)
        val tree = NavNode.Stack(TestKey("stack"), listOf(child1, child2))

        assertTrue(tree.navigateUp())
        assertEquals(2, tree.children.size)
        assertEquals(child1.key, tree.children[0].key)
        assertEquals(upNode.key, tree.children[1].key)
    }

    @Test
    fun select_navigateUp_delegatesToSelectedChild() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val child2 = NavNode.Leaf(TestKey("child2"))
        val childStack = NavNode.Stack(TestKey("childStack"), listOf(child1, child2))
        val tree = NavNode.Select(TestKey("select"), childStack.key, setOf(childStack))

        assertTrue(tree.navigateUp())
        assertEquals(1, childStack.children.size)
        assertEquals(child1.key, childStack.children.last().key)
    }

    @Test
    fun stack_pop_removesLastChild() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val child2 = NavNode.Leaf(TestKey("child2"))
        val tree = NavNode.Stack(TestKey("stack"), listOf(child1, child2))

        assertTrue(tree.pop())
        assertEquals(1, tree.children.size)
        assertEquals(child1.key, tree.children.last().key)
    }

    @Test
    fun stack_pop_sizeOne_returnsFalse() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val tree = NavNode.Stack(TestKey("stack"), listOf(child1))

        assertFalse(tree.pop())
        assertEquals(1, tree.children.size)
        assertEquals(child1.key, tree.children.last().key)
    }

    @Test
    fun stack_pop_withRoute() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val child2 = NavNode.Leaf(TestKey("child2"))
        val child3 = NavNode.Leaf(TestKey("child3"))
        val tree = NavNode.Stack(TestKey("stack"), listOf(child1, child2, child3))

        assertTrue(tree.pop(route = child2.key, inclusive = false))
        assertEquals(2, tree.children.size)
        assertEquals(child2.key, tree.children.last().key)
    }

    @Test
    fun stack_pop_withRoute_inclusive() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val child2 = NavNode.Leaf(TestKey("child2"))
        val child3 = NavNode.Leaf(TestKey("child3"))
        val tree = NavNode.Stack(TestKey("stack"), listOf(child1, child2, child3))

        assertTrue(tree.pop(route = child2.key, inclusive = true))
        assertEquals(1, tree.children.size)
        assertEquals(child1.key, tree.children.last().key)
    }

    @Test
    fun stack_pop_routeNotFound_returnsFalse() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val tree = NavNode.Stack(TestKey("stack"), listOf(child1))

        assertFalse(tree.pop(route = TestKey("notFound"), inclusive = false))
        assertEquals(child1.key, tree.children.last().key)
    }

    @Test
    fun select_pop_delegatesToSelectedChild() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val child2 = NavNode.Leaf(TestKey("child2"))
        val childStack = NavNode.Stack(TestKey("childStack"), listOf(child1, child2))
        val tree = NavNode.Select(TestKey("select"), childStack.key, setOf(childStack))

        assertTrue(tree.pop())
        assertEquals(1, childStack.children.size)
        assertEquals(child1.key, childStack.children.last().key)
    }

    @Test
    fun select_popWithRoute_delegatesToSelectedChild() {
        val child1 = NavNode.Leaf(TestKey("child1"))
        val child2 = NavNode.Leaf(TestKey("child2"))
        val child3 = NavNode.Leaf(TestKey("child3"))
        val childStack = NavNode.Stack(TestKey("childStack"), listOf(child1, child2, child3))
        val tree = NavNode.Select(TestKey("select"), childStack.key, setOf(childStack))

        assertTrue(tree.pop(route = child2.key, inclusive = false))
        assertEquals(2, childStack.children.size)
        assertEquals(child2.key, childStack.children.last().key)
    }
}
