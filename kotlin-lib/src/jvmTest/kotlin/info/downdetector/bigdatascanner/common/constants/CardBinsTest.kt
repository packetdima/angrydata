package info.downdetector.bigdatascanner.common.constants

import kotlin.test.Test

import kotlin.test.assertEquals

internal class CardBinsTest {

    @Test
    fun getCardBins() {
        assertEquals(true, CardBins.cardBins.isNotEmpty())
        assertEquals(true, CardBins.cardBins.contains("3585"))
    }
}