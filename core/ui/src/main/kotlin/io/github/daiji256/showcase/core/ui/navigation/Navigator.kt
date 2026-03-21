package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey

/**
 * Navigator for [NavState].
 *
 * @param state the [NavState]
 */
class Navigator(private val state: NavState) {
    /**
     * push the [key]
     *
     * @param key the key to push
     * @return `true` if the key was pushed, `false` otherwise
     */
    fun push(key: NavKey): Boolean = state.root.push(key)

    /**
     * pop the back
     *
     * @return `true` if backed, `false` otherwise
     */
    fun pop(): Boolean = state.root.pop()

    /**
     * switch to the [to] key from the [from] key
     *
     * if not found [to], create a new [NavNode] and switch to it
     *
     * @param from the key to switch from
     * @param to the key to switch to
     * @return `true` if the keys were switched, `false` otherwise
     */
    fun switch(from: NavKey, to: NavKey): Boolean = this.switch(setOf(from), listOf(to))

    /**
     * switch to the [to] keys from the [from] key
     *
     * if not found [to], create a new [NavNode] and switch to it
     *
     * @param from the key to switch from
     * @param to the keys to switch to
     * @return `true` if the keys were switched, `false` otherwise
     */
    fun switch(from: NavKey, to: List<NavKey>): Boolean = this.switch(setOf(from), to)

    /**
     * switch to the [to] key from the [from] keys
     *
     * if not found [to], create a new [NavNode] and switch to it
     *
     * @param from the keys to switch from
     * @param to the key to switch to
     * @return `true` if the keys were switched, `false` otherwise
     */
    fun switch(from: Set<NavKey>, to: NavKey): Boolean = this.switch(from, listOf(to))

    /**
     * switch to the [to] keys from the [from] keys
     *
     * if not found [to], create a new [NavNode] and switch to it
     *
     * @param from the keys to switch from
     * @param to the keys to switch to
     * @return `true` if the keys were switched, `false` otherwise
     */
    fun switch(from: Set<NavKey>, to: List<NavKey>): Boolean = state.root.switch(from, to)

    /**
     * navigate up
     *
     * @return `true` if navigation was handled, `false` otherwise
     */
    fun navigateUp(): Boolean {
        if (this.pop()) return true
        val pending = state.pending.removeLastOrNull() ?: return false
        val pendingChild = state.root.children.find { it.key == pending } ?: NavNode(key = pending)
        state.root.children.removeAll { it == state.root.currentChild }
        state.root.currentChild = pendingChild
        state.root.children += pendingChild
        return true
    }

    /**
     * restart
     *
     * @param start the start [NavKey]
     * @param pending the pending keys to navigate up
     */
    fun restart(start: NavKey, pending: List<NavKey> = listOf()) {
        val child = NavNode(key = start)
        state.root.currentChild = child
        state.root.children.clear()
        state.root.children += child
        state.pending.clear()
        state.pending += pending
    }
}
