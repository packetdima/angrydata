package ru.packetdima.datascanner.common

class Cleaner {
    /*This class is responsible for cleaning documents text*/
    companion object {
        // delete all junk from text
        private fun delGarbageCharacters(text: String): String {
            val patternTags = """<[^>]*?>""".toRegex()
            val pattern = """([^а-яА-Яa-zA-Z@,.:0-9- ])|(nbsp)|(quot)""".toRegex()
            return text.replace(patternTags, "  ").replace(pattern, "  ")
        }

        // normalize spaces
        private fun delExtraSpaces(text: String): String {
            return text.replace("""\s{3,}""".toRegex(), "  ")
        }

        // getValue cleaned text
        fun cleanText(text: String): String {
            return delExtraSpaces(delGarbageCharacters(text)).trim()
        }
    }
}
