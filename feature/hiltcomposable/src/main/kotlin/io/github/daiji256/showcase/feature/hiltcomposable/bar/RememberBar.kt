package io.github.daiji256.showcase.feature.hiltcomposable.bar

import androidx.compose.runtime.Composable
import io.github.daiji256.showcase.feature.hiltcomposable.hilt.rememberComposableEntryPoint

@Composable
internal fun rememberBar(): Bar = rememberComposableEntryPoint<BarEntryPoint>().bar
