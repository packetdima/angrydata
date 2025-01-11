package info.downdetector.bigdatascanner.common

interface IDetectFunction {
    val name: String
    val writeName: String

    fun scan(text: String): Int
}