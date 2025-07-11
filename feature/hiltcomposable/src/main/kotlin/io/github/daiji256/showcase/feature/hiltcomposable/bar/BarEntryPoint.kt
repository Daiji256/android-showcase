package io.github.daiji256.showcase.feature.hiltcomposable.bar

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import io.github.daiji256.showcase.feature.hiltcomposable.hilt.ComposableComponent

@EntryPoint
@InstallIn(ComposableComponent::class)
interface BarEntryPoint {
    val bar: Bar
}
