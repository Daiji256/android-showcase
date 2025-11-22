package io.github.daiji256.showcase.core.ui.showcase

import androidx.compose.runtime.Stable

@Stable
class Feature(
    val title: String,
    val onNavigateClick: () -> Unit,
)
