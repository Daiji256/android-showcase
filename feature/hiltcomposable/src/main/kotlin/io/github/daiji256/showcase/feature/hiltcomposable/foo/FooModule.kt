package io.github.daiji256.showcase.feature.hiltcomposable.foo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object FooModule {
    @Provides
    fun providesFoo(): Foo = Foo()
}
