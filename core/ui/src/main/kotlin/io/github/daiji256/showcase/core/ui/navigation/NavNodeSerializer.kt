package io.github.daiji256.showcase.core.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal class NavNodeSerializer : KSerializer<NavNode> {
    private val dtoSerializer: KSerializer<NavNodeDto> = NavNodeDto.serializer()

    override val descriptor: SerialDescriptor = dtoSerializer.descriptor

    override fun serialize(encoder: Encoder, value: NavNode) =
        dtoSerializer.serialize(encoder, value.toNavNodeDto())

    override fun deserialize(decoder: Decoder): NavNode =
        dtoSerializer.deserialize(decoder).toNavNode()

    private fun NavNode.toNavNodeDto(): NavNodeDto =
        NavNodeDto(
            key = this.key,
            currentChildKey = this.currentChild?.key,
            children = this.children.map { it.toNavNodeDto() },
        )

    private fun NavNodeDto.toNavNode(): NavNode =
        NavNode(key = this.key).also { node ->
            node.children += this.children.map { it.toNavNode() }
            node.currentChild = node.children.firstOrNull { it.key == this.currentChildKey }
        }

    @Serializable
    private data class NavNodeDto(
        @SerialName("k")
        val key: SerializableAnyNavKey,
        @SerialName("ck")
        val currentChildKey: SerializableAnyNavKey?,
        @SerialName("cs")
        val children: List<NavNodeDto>,
    )

    private typealias SerializableAnyNavKey =
        @Serializable(with = AnyNavKeySerializer::class)
        NavKey
}
