package info.downdetector.bigdatascanner.common

import kotlin.test.assertEquals
import kotlin.test.Test

internal class CleanerTest{

    @Test
    fun testClearText() {
        val expected = "Some various  text  and other  Information"
        assertEquals(expected, Cleaner.cleanText("{Some various [text]<tags> and other} Information"))
    }

    @Test
    fun testCleaner() {
        val expected = Cleaner
        assertEquals(expected, Cleaner)
    }
}