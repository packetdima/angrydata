package info.downdetector.bigdatascanner.common.constants

import java.io.BufferedReader
import java.io.InputStreamReader

actual object CardBins {
    private const val BINS_FILE_NAME = "values/CardBins.txt"
    actual val cardBins: List<String> = (
            this::class.java.classLoader.getResourceAsStream(BINS_FILE_NAME)
                ?: throw IllegalArgumentException("$BINS_FILE_NAME is not found")
            ).use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).readLines()
        }
}