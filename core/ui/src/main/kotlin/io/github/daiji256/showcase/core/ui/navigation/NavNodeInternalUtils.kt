package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey

internal fun NavNode.push(key: NavKey): Boolean {
    // delegate push to current child
    val currentChild = this.currentChild
    if (currentChild != null) return currentChild.push(key)

    // if same key, no push needed (single top)
    if (this.key == key) return false

    // push new child
    val newChild = NavNode(key = key)
    this.currentChild = newChild
    this.children += newChild
    return true
}

internal fun NavNode.pop(): Boolean {
    // delegate pop to current child
    val currentChild = this.currentChild ?: return false
    if (currentChild.pop()) return true

    // if it will be empty, don't pop
    if (this.key == RootNavKey) return false

    // pop
    this.currentChild = null
    this.children.clear()
    return true
}

internal fun NavNode.switch(from: Set<NavKey>, to: List<NavKey>): Boolean {
    if (from.isEmpty() || to.isEmpty()) return false

    // if not found `from` in the children, delegate to the current child
    if (!this.children.any { it.key in from }) {
        return this.currentChild?.switch(from, to) == true
    }

    // if found `to` in the children, switch to it
    // else create a new child and switch to it
    val existingChild = to.firstNotNullOfOrNull { t -> this.children.find { it.key == t } }
    if (existingChild != null) {
        this.currentChild = existingChild
    } else {
        val newChild = NavNode(key = to.first())
        this.currentChild = newChild
        this.children += newChild
    }
    return true
}
