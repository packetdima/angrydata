package detectfunctions

import info.downdetector.bigdatascanner.common.DetectFunction


fun detectEmails(text: String): Int {
    return DetectFunction.Emails.scan(text)
}

fun detectPhones(text: String): Int {
    return DetectFunction.Phones.scan(text)
}

fun detectCardNumbers(text: String): Int {
    return DetectFunction.CardNumbers.scan(text)
}

fun detectCarNumber(text: String): Int {
    return DetectFunction.CarNumber.scan(text)
}

fun detectSINLS(text: String): Int {
    return DetectFunction.SNILS.scan(text)
}

fun detectPassport(text: String): Int {
    return DetectFunction.Passport.scan(text)
}

fun detectOMS(text: String): Int {
    return DetectFunction.OMS.scan(text)
}

fun detectINN(text: String): Int {
    return DetectFunction.INN.scan(text)
}

fun detectAccountNumber(text: String): Int {
    return DetectFunction.AccountNumber.scan(text)
}

fun detectAddress(text: String): Int {
    return DetectFunction.Address.scan(text)
}

fun detectValuableInfo(text: String): Int {
    return DetectFunction.ValuableInfo.scan(text)
}

fun detectLogin(text: String): Int {
    return DetectFunction.Login.scan(text)
}

fun detectPassword(text: String): Int {
    return DetectFunction.Password.scan(text)
}

fun detectCVV(text: String): Int {
    return DetectFunction.CVV.scan(text)
}

fun detectName(text: String): Int {
    return DetectFunction.Name.scan(text)
}

fun detectIP(text: String): Int {
    return DetectFunction.IP.scan(text)
}

fun detectIPv6(text: String): Int {
    return DetectFunction.IPv6.scan(text)
}
