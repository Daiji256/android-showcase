package io.github.daiji256.showcase.feature.hiltcomposable.hilt

import dagger.hilt.DefineComponent
import dagger.hilt.android.components.ActivityComponent

@ComposableScoped
@DefineComponent(parent = ActivityComponent::class)
interface ComposableComponent
