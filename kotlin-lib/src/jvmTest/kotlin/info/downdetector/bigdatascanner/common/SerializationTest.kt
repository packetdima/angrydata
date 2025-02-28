package info.downdetector.bigdatascanner.common

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlin.test.Test
import kotlin.test.assertEquals


internal class SerializationTest {
    private val serializerModule = SerializersModule {
        polymorphic(IDetectFunction::class) {
            subclass(DetectFunction::class, DetectFunctionSerializer())
        }
    }
    private val format = Json { serializersModule = serializerModule }

    @Test
    fun `Single detect function serialization`() {
        DetectFunction.entries.forEach { df ->
            val serialized = format.encodeToString(df)
            assertEquals(df, format.decodeFromString( serialized))
        }
    }

    @Test
    fun `List of detect function serialization`() {
        val list = DetectFunction.entries.toMutableList()
        val serialized = format.encodeToString(list)
        assertEquals(list, format.decodeFromString( serialized))
    }
}