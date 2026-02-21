package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.serialization.NavKeySerializer
import kotlinx.serialization.Serializable

@Serializable
internal sealed interface NavNodeDto<T : NavKey> {
    @Serializable
    data class Key<T : NavKey>(
        val key:
        @Serializable(with = NavKeySerializer::class)
        T,
    ) : NavNodeDto<T>

    @Serializable
    data class Stack<T : NavKey>(
        val children: List<
            NavNodeDto<
                @Serializable(with = NavKeySerializer::class)
                T,
                >,
            >,
    ) : NavNodeDto<T>

    @Serializable
    data class Select<T : NavKey>(
        val selected:
        @Serializable(with = NavKeySerializer::class)
        T,
        val children: List<
            Child<
                @Serializable(with = NavKeySerializer::class)
                T,
                >,
            >,
    ) : NavNodeDto<T> {
        @Serializable
        data class Child<T : NavKey>(
            val key:
            @Serializable(with = NavKeySerializer::class)
            T,
            val stack: Stack<
                @Serializable(with = NavKeySerializer::class)
                T,
                >,
        )
    }
}
