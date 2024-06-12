package com.zhixue.lite.core.common.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder

@Serializable(JsonStringSerializer::class)
data class JsonString<T>(val value: T)

private class JsonStringSerializer<T>(
    private val serializer: KSerializer<T>
) : KSerializer<JsonString<T>> {

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, value: JsonString<T>) {}

    override fun deserialize(decoder: Decoder): JsonString<T> {
        check(decoder is JsonDecoder)
        return JsonString(decoder.json.decodeFromString(serializer, decoder.decodeString()))
    }
}