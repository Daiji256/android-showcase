package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.toMutableStateList
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal class NavStateSerializer : KSerializer<NavState> {
    private val dtoSerializer: KSerializer<NavStateDto> = NavStateDto.serializer()

    override val descriptor: SerialDescriptor = dtoSerializer.descriptor

    override fun serialize(encoder: Encoder, value: NavState) =
        dtoSerializer.serialize(
            encoder = encoder,
            value = NavStateDto(
                root = value.root,
                pending = value.pending.toList(),
            ),
        )

    override fun deserialize(decoder: Decoder): NavState {
        val dto = dtoSerializer.deserialize(decoder)
        return NavState(
            root = dto.root,
            pending = dto.pending.toMutableStateList(),
        )
    }

    @Serializable
    private data class NavStateDto(
        @SerialName("r")
        val root: NavNode,
        @SerialName("p")
        val pending: List<SerializableAnyNavKey>,
    )

    private typealias SerializableAnyNavKey =
        @Serializable(with = AnyNavKeySerializer::class)
        NavKey
}
