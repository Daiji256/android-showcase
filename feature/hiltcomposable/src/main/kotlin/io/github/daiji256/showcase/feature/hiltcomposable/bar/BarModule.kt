package io.github.daiji256.showcase.feature.hiltcomposable.bar

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import io.github.daiji256.showcase.feature.hiltcomposable.hilt.ComposableComponent

@Module
@InstallIn(ComposableComponent::class)
internal object BarModule {
    @Provides
    fun providesBar(): Bar = Bar(name = "Bar")
}
