package io.github.daiji256.showcase.feature.hiltcomposable.hilt

import dagger.hilt.DefineComponent

@DefineComponent.Builder
interface ComposableComponentBuilder {
    fun build(): ComposableComponent
}
