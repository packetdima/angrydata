package info.downdetector.bigdatascanner.common

import info.downdetector.bigdatascanner.common.constants.CardBins
import info.downdetector.bigdatascanner.common.etenxsions.getNumericValue
import kotlinx.serialization.Serializable

@Suppress("unused")
@Serializable(with = DetectFunctionSerializer::class)
enum class DetectFunction(override val writeName: String) : IDetectFunction {
    /** This class is responsible on detection and
     * extraction information from documents
     **/
    Emails("emails"),
    Phones("phones"),
    CardNumbers("card_numbers"),
    CarNumber("car_numbers"),
    SNILS("snilses"),
    Passport("passports"),
    OMS("omses"),
    INN("inns"),
    AccountNumber("account_number"),
    Address("address"),
    BlackList("black_list"),
    ValuableInfo("valuable_info"),
    Login("logins"),
    Password("passwords"),
    CVV("cvv"),
    Name("full_names"),
    IP("ips"),
    IPv6("ipv6s");

    override fun scan(text: String): Int = when (this) {
        Emails -> regexDetector(
            text,
            """(?<=[-, ()=*]|^)[a-zA-Z0-9_.+-]+@[a-z0-9-.]+?\.[a-z]{2,}(?=\W|$)"""
                .toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
        ).count()

        Phones -> regexDetector(
            text,
            """(?<=[-, ()=*]|^)((\+?7)|8)[ \t\-]?\(?[489][0-9]{2}\)?[ \t\-]?[0-9]{3}[ \t\-]?[0-9]{2}[ \t\-]?[0-9]{2}(?=\W|$)"""
                .toRegex(setOf(RegexOption.MULTILINE))
        ).count()

        CardNumbers -> {
            fun isBinValid(card: String): Boolean {
                return CardBins.cardBins.contains(card.substring(0, 4))
            }

            fun isCardValid(card: String): Boolean {
                var controlSum = 0
                for (index in card.indices) {
                    controlSum += if ((index % 2) == 0) {
                        val count = 2 * card[index].getNumericValue()
                        if (count <= 9) count else count - 9
                    } else {
                        card[index].getNumericValue()
                    }
                }
                return controlSum % 10 == 0
            }

            val cards = regexDetector(
                text,
                """(?<=[-:,()=*\s]|^)(([0-9]{4}[ ][0-9]{4}[ ][0-9]{4}[ ][0-9]{4})|([0-9]{16}))(?=[-(),*\s]|$)"""
                    .toRegex(setOf(RegexOption.MULTILINE))
            )
            cards.map {
                it.replace(" ", "").replace("-", "").trim()
            }.filter {
                it != "0000000000000000"
                        && isBinValid(it)
                        && isCardValid(it)
            }.distinct().count()
        }

        CarNumber -> regexDetector(
            text,
            """(гос|номер|авто|рег).{0,15}([авекмнорстух][ \t]?[0-9]{3}[ \t]?[авекмнорстух]{2}[ \t]?[0-9]{2,3})"""
                .toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
        ).count()

        SNILS -> {
            /**
             * This function checks if the given SNILS is correct.
             * @param input the SNILS to check
             * @return true if the SNILS is correct, false otherwise
             */
            fun isSnilsCorrect(input: String): Boolean {
                var summ = 0
                val snils = input.replace(" ", "").replace("-", "").trim()

                for (index in 0 until snils.length - 2) {
                    summ += snils[index].getNumericValue() * (9 - index)
                }
                val controlSum = if (listOf(100, 101).contains(summ)) {
                    "00"
                } else if (summ > 101) {
                    (summ % 101).toString()
                } else {
                    summ.toString()
                }
                return snils.substring(snils.length - 2 until snils.length) == controlSum
            }

            regexDetector(
                text,
                """(?<=[-,()=*\s]|^)[0-9]{3}[ -]?[0-9]{3}[ -]?[0-9]{3}[ -]?[0-9]{2}(?=[-(),*\s]|$)"""
                    .toRegex(setOf(RegexOption.MULTILINE))
            ).filter {
                isSnilsCorrect(it)
            }.count()
        }

        Passport -> (regexDetector(
            text,
            """([п]аспорт[ \t-]?([а-яА-Я]*[ \t-]){0,2}[0-9]{2}[ \t]?[0-9]{2}[ \t]?[0-9]{6})"""
                .toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
        ) + regexDetector(
            text,
            """[сc]ерия[ \t-]?[0-9]{2}(\s|\t)?[0-9]{2}[ \t,]?([н]омер)?[ \t-]?[0-9]{6}?"""
                .toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
        )).distinct().count()

        OMS -> {
            // validate oms
            /**
             * Checks if the given OMS is correct.
             * @param input the OMS to check
             * @return true if the OMS is correct, false otherwise
             */
            fun isOmsValid(input: String): Boolean {
                val oms = input.replace("-", "").replace("""\s+""".toRegex(), "")
                val key = oms.last().getNumericValue()
                val odd = mutableListOf<Char>()  // nechet
                val even = mutableListOf<Char>() // chet
                oms.substring(0 until oms.length - 1).reversed().forEachIndexed { index, digit ->
                    // it's odd because starts with index = 0
                    if (index % 2 == 0) {
                        odd.add(digit)
                    } else {
                        even.add(digit)
                    }
                }
                val right = (odd.joinToString(separator = "").toInt() * 2).toString()
                // getValue sum of all elements
                var summ = 0
                for (elem in even)
                    summ += elem.getNumericValue()
                for (elem in right)
                    summ += elem.getNumericValue()
                // nearest value more or equal sum and sum % 10 = 0 minus sum
                val checker = 10 - summ % 10
                return checker == key || (checker == 10 && key == 0)
            }

            customRegexDetector(
                text,
                """(?<=\D|^)(?<=(омс|полис|страховка|страхование))(\s)[0-9]{4}[ \t-]*?[0-9]{4}[ \t-]*?[0-9]{4}[ \t-]*?[0-9]{4}(?=\D|$)"""
                    .toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
            ).filter {
                isOmsValid(it)
            }.count()
        }

        INN -> {
            // validate inn
            /**
             * Checks if the given INN is correct.
             * @param input the INN to check
             * @return true if the INN is correct, false otherwise
             */
            fun isInnValid(input: String): Boolean {
                val inn = input.replace("-", "").replace(" ", "").trim()
                // control sequences
                val firstSequence = listOf(7, 2, 4, 10, 3, 5, 9, 4, 6, 8)
                val secondSequence = listOf(3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8)
                // first control sum
                var summ1 = 0
                for (index in firstSequence.indices)
                    summ1 += firstSequence[index] * inn[index].getNumericValue()
                val key1 = (summ1 % 11).toString().last()
                // second control sum
                var summ2 = 0
                for (index in secondSequence.indices)
                    summ2 += secondSequence[index] * inn[index].getNumericValue()
                val key2 = (summ2 % 11).toString().last()
                return key1 == inn[10] && key2 == inn[11]
            }
            customRegexDetector(
                text,
                """(?<=[-:,()=*\s]|^)[0-9]{12}(?=[-(),*\s]|$)"""
                    .toRegex(setOf(RegexOption.MULTILINE))
            ).filter {
                isInnValid(it)
            }.count()
        }

        AccountNumber -> regexDetector(
            text,
            """(?<=\D|^)40[0-9]{3}(810|840|978)[0-9]{12}(?=\D|$)"""
                .toRegex(setOf(RegexOption.MULTILINE))
        ).count()

        Address -> regexDetector(
            text,
            """(г\.|р-н|обл\.|ул\.|гор\.).{4,70}(д\.|дом)"""
                .toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
        ).count()

        BlackList -> regexDetector(
            text,
            """(\.|\s|^)(БЕЗОПАСНОСТ|ВНУТРИБАНК|ФСБ|ФЕДЕРАЛ|ФСО|РАЗВЕДК|НАЦИОНАЛЬН|ГВАРДИ|МИНИСТЕРСТВО|МВД|ОБОРОН|МЧС|ПРЕМЬЕР|VIP|МВС|МВК|СКУД|ИНКАССАЦИЯ|ГОСУДАРСТВ)(\.|\s|$)"""
                .toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
        ).count()

        ValuableInfo -> regexDetector(
            text,
            """(\.|\s|^)(СЕКРЕТ|КОНФИДЕНЦИАЛЬН|КОМПЕНСАЦ|КОММЕРЧ|ТАЙНА|КЛЮЧ|ШИФР|PIN|SECRET|PRIVACY|ДЕТАЛИ ПЛАТЕЖА|НАЗНАЧЕНИЕ ПЛАТЕЖА|DETAILS OF PAYMENT|PAYMENT DETAILS)(\.|\s|$)"""
                .toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
        ).count()

        Login -> regexDetector(
            text,
            """(логин|login)(:|\s)\s?[a-z0-9_-]\S{3,25}"""
                .toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
        ).count()

        Password -> regexDetector(
            text,
            """(((password|пароль)\s((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@$}{'?;,:=+_\-]*))\S{3,25})|((password|пароль):\s?\S{3,25}))"""
                .toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
        ).count()

        CVV -> regexDetector(
            text,
            """(\.|\s|^)(cvc|cvv|cav|cvc2|cvv2|cav2)(:|\s|:\s)[0-9]{3}(\.|\s|$)"""
                .toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
        ).count()

        Name -> regexDetector(
            text,
            """(^|\s)((([А-ЯЁ][а-яё]+\s)([А-ЯЁ][а-яё]+[ая]\s)([А-ЯЁ][а-яё]+на))|(([А-ЯЁ][а-яё]+\s)([А-ЯЁ][а-яё]+[^ая]\s)([А-ЯЁ][а-яё]+(ич|ь))))($|\W|\s)"""
                .toRegex(setOf(RegexOption.MULTILINE))
        ).count()

        IP -> regexDetector(
            text,
            """(^|\s)((25[0-5]|(2[0-4]|1\d|[1-9]|)\d)\.){3}(25[0-5]|(2[0-4]|1\d|[1-9]|)\d)(${'$'}|\s)"""
                .toRegex(setOf(RegexOption.MULTILINE))
        ).count()

        IPv6 -> regexDetector(
            text,
            """(^|\s)(([0-9a-fA-F]{4}:){7}[0-9a-fA-F]{4})(${'$'}|\s)""" // только адреса без сокращений
                .toRegex(setOf(RegexOption.MULTILINE))
        ).count()
    }
}


private fun regexDetector(text: String, regex: Regex): Sequence<String> {
    return customRegexDetector(text, regex).distinct()
}

private fun customRegexDetector(text: String, regex: Regex): Sequence<String> {
    return regex.findAll(text).map { it.value }.distinct()
}