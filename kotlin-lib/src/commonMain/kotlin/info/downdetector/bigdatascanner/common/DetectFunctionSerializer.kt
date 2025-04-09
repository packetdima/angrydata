package info.downdetector.bigdatascanner.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class DetectFunctionSerializer : KSerializer<DetectFunction> {
    @Serializable
    @SerialName("String")
    data class StringSurrogate(val detectFunction: String)

    override val descriptor: SerialDescriptor = StringSurrogate.serializer().descriptor

    override fun deserialize(decoder: Decoder): DetectFunction {

        val detectFunction = decoder.decodeSerializableValue(StringSurrogate.serializer()).detectFunction
        if (detectFunction == "BlackList") return DetectFunction.ValuableInfo
        return DetectFunction
            .entries
            .firstOrNull {
                it.name == detectFunction
            }
            ?: throw IllegalArgumentException("Unknown DetectFunction: $detectFunction")
    }

    override fun serialize(encoder: Encoder, value: DetectFunction) {
        StringSurrogate.serializer().serialize(encoder, StringSurrogate(value.name))
    }
}