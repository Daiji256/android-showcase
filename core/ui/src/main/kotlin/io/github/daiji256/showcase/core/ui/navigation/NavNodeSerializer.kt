package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal class NavNodeLeafSerializer<T : NavKey>(
    navKeySerializer: KSerializer<T>,
) : KSerializer<NavNode.Leaf<T>> {
    private val dtoSerializer: KSerializer<NavNodeDto.Leaf<T>> =
        NavNodeDto.Leaf.serializer(navKeySerializer)

    override val descriptor: SerialDescriptor =
        dtoSerializer.descriptor

    override fun serialize(encoder: Encoder, value: NavNode.Leaf<T>) =
        dtoSerializer.serialize(encoder, value.toNavNodeDto() as NavNodeDto.Leaf<T>)

    override fun deserialize(decoder: Decoder): NavNode.Leaf<T> =
        dtoSerializer.deserialize(decoder).toNavNode() as NavNode.Leaf<T>
}

internal class NavNodeStackSerializer<T : NavKey>(
    navKeySerializer: KSerializer<T>,
) : KSerializer<NavNode.Stack<T>> {
    private val dtoSerializer: KSerializer<NavNodeDto.Stack<T>> =
        NavNodeDto.Stack.serializer(navKeySerializer)

    override val descriptor: SerialDescriptor =
        dtoSerializer.descriptor

    override fun serialize(encoder: Encoder, value: NavNode.Stack<T>) =
        dtoSerializer.serialize(encoder, value.toNavNodeDto() as NavNodeDto.Stack<T>)

    override fun deserialize(decoder: Decoder): NavNode.Stack<T> =
        dtoSerializer.deserialize(decoder).toNavNode() as NavNode.Stack<T>
}

internal class NavNodeSelectSerializer<T : NavKey>(
    navKeySerializer: KSerializer<T>,
) : KSerializer<NavNode.Select<T>> {
    private val dtoSerializer: KSerializer<NavNodeDto.Select<T>> =
        NavNodeDto.Select.serializer(navKeySerializer)

    override val descriptor: SerialDescriptor =
        dtoSerializer.descriptor

    override fun serialize(encoder: Encoder, value: NavNode.Select<T>) =
        dtoSerializer.serialize(encoder, value.toNavNodeDto() as NavNodeDto.Select<T>)

    override fun deserialize(decoder: Decoder): NavNode.Select<T> =
        dtoSerializer.deserialize(decoder).toNavNode() as NavNode.Select<T>
}

private fun <T : NavKey> NavNode<T>.toNavNodeDto(): NavNodeDto<T> =
    when (this) {
        is NavNode.Leaf ->
            NavNodeDto.Leaf(key = key)

        is NavNode.Stack ->
            NavNodeDto.Stack(
                children = children.map { it.toNavNodeDto() },
            )

        is NavNode.Select ->
            NavNodeDto.Select(
                selected = selected,
                children = children.map { (key, node) ->
                    NavNodeDto.Select.Child(key, node.toNavNodeDto())
                },
            )
    }

private fun <T : NavKey> NavNodeDto<T>.toNavNode(): NavNode<T> =
    when (this) {
        is NavNodeDto.Leaf ->
            NavNode.Leaf(key = key)

        is NavNodeDto.Stack ->
            NavNode.Stack(
                children = children.map { it.toNavNode() },
            )

        is NavNodeDto.Select ->
            NavNode.Select(
                selected = selected,
                children = children.associate { (key, node) ->
                    key to node.toNavNode()
                },
            )
    }
