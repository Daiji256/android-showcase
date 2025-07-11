package io.github.daiji256.showcase.feature.hiltcomposable.hilt

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ComposableComponentBuilderEntryPoint {
    val builder: ComposableComponentBuilder
}
