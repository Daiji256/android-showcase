package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.serialization.NavKeySerializer
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
internal sealed interface NavNodeDto<T : NavKey> {
    @Serializable
    data class Key<T : NavKey>(
        val navKey:
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
        val id: Uuid,
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
            val navKey:
            @Serializable(with = NavKeySerializer::class)
            T,
            val stack: Stack<
                @Serializable(with = NavKeySerializer::class)
                T,
                >,
        )
    }
}
