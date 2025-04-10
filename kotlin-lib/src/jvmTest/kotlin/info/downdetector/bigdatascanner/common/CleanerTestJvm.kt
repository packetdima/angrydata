package info.downdetector.bigdatascanner.common

import kotlin.test.assertEquals
import kotlin.test.Test

internal class CleanerTestJvm {

    @Test
    fun testDelGarbageCharacters() {
        val method = Cleaner::class.java.getDeclaredMethod("delGarbageCharacters", String::class.java)
        method.isAccessible = true
        val parameters = arrayOfNulls<Any>(1)
        parameters[0] = "{Some various [text]<tags> and other} Information"
        val expected = "  Some various   text     and other   Information"

        assertEquals(expected, method.invoke(Cleaner, *parameters))
    }
}