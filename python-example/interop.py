import ctypes
from ctypes import c_bool, c_char, c_short, c_int, c_long, c_ubyte, c_ushort, c_uint, c_byte, c_float, c_double, POINTER, Structure, c_ulong, CFUNCTYPE, c_char_p

lib = ctypes.CDLL("../library/build/bin/native/releaseShared/AngryData.dll")
# Define the necessary types
class AngryData_KNativePtr(Structure):
    pass  # Define the structure as needed

class AngryData_KType(Structure):
    pass  # Define the structure as needed

class AngryData_KBoolean(c_bool):
    pass  # Assuming it's an integer type

class AngryData_KByte(c_byte):
    pass  # Assuming it's a byte type

class AngryData_KShort(c_short):
    pass  # Assuming it's a short type

class AngryData_KInt(c_int):
    pass  # Assuming it's an int type

class AngryData_KLong(c_long):
    pass  # Assuming it's a long type

class AngryData_KFloat(c_float):
    pass  # Assuming it's a float type

class AngryData_KDouble(c_double):
    pass  # Assuming it's a double type

class AngryData_KChar(c_char):
    pass  # Assuming it's a wchar type

class AngryData_KUByte(c_ubyte):
    pass  # Assuming it's an unsigned byte type

class AngryData_KUShort(c_ushort):
    pass  # Assuming it's an unsigned short type

class AngryData_KUInt(c_uint):
    pass  # Assuming it's an unsigned int type

class AngryData_KULong(c_ulong):
    pass  # Assuming it's an unsigned long type

# Define function pointer types
DisposeStablePointerType = CFUNCTYPE(None, AngryData_KNativePtr)
DisposeStringType = CFUNCTYPE(None, c_char_p)
IsInstanceType = CFUNCTYPE(AngryData_KBoolean, AngryData_KNativePtr, POINTER(AngryData_KType))

createNullableByteType = CFUNCTYPE(POINTER(AngryData_KByte), AngryData_KByte)
getNonNullValueOfByteType = CFUNCTYPE(AngryData_KByte, POINTER(AngryData_KByte))

createNullableShortType = CFUNCTYPE(POINTER(AngryData_KShort), AngryData_KShort)
getNonNullValueOfShortType = CFUNCTYPE(AngryData_KShort, POINTER(AngryData_KShort))

createNullableIntType = CFUNCTYPE(POINTER(AngryData_KInt), AngryData_KInt)
getNonNullValueOfIntType = CFUNCTYPE(AngryData_KInt, POINTER(AngryData_KInt))

createNullableLongType = CFUNCTYPE(POINTER(AngryData_KLong), AngryData_KLong)
getNonNullValueOfLongType = CFUNCTYPE(AngryData_KLong, POINTER(AngryData_KLong))

createNullableFloatType = CFUNCTYPE(POINTER(AngryData_KFloat), AngryData_KFloat)
getNonNullValueOfFloatType = CFUNCTYPE(AngryData_KFloat, POINTER(AngryData_KFloat))

createNullableDoubleType = CFUNCTYPE(POINTER(AngryData_KDouble), AngryData_KDouble)
getNonNullValueOfDoubleType = CFUNCTYPE(AngryData_KDouble, POINTER(AngryData_KDouble))

createNullableCharType = CFUNCTYPE(POINTER(AngryData_KChar), AngryData_KChar)
getNonNullValueOfCharType = CFUNCTYPE(AngryData_KChar, POINTER(AngryData_KChar))

createNullableBooleanType = CFUNCTYPE(POINTER(AngryData_KBoolean), AngryData_KBoolean)
getNonNullValueOfBooleanType = CFUNCTYPE(AngryData_KBoolean, POINTER(AngryData_KBoolean))

createNullableUnitType = CFUNCTYPE(POINTER(None))  # Assuming it returns void

createNullableUByteType = CFUNCTYPE(POINTER(AngryData_KUByte), AngryData_KUByte)
getNonNullValueOfUByteType = CFUNCTYPE(AngryData_KUByte, POINTER(AngryData_KUByte))

createNullableUShortType = CFUNCTYPE(POINTER(AngryData_KUShort), AngryData_KUShort)
getNonNullValueOfUShortType = CFUNCTYPE(AngryData_KUShort, POINTER(AngryData_KUShort))

createNullableUIntType = CFUNCTYPE(POINTER(AngryData_KUInt), AngryData_KUInt)
getNonNullValueOfUIntType = CFUNCTYPE(AngryData_KUInt, POINTER(AngryData_KUInt))

createNullableULongType = CFUNCTYPE(POINTER(AngryData_KULong), AngryData_KULong)
getNonNullValueOfULongType = CFUNCTYPE(AngryData_KULong, POINTER(AngryData_KULong))


class Cleaner(Structure):
    _fields_ = [
        ("cleanText", CFUNCTYPE(c_char_p, c_char_p))
    ]

class DetectFunctions(Structure):
    _fields_ = [
        ("detectAccountNumber", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectAddress", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectCVV", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectCarNumber", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectCardNumbers", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectEmails", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectINN", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectIP", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectIPv6", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectLogin", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectName", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectOMS", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectPassport", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectPassword", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectPhones", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectSINLS", CFUNCTYPE(AngryData_KInt, c_char_p)),
        ("detectValuableInfo", CFUNCTYPE(AngryData_KInt, c_char_p))
    ]

# Define the root structure
class Root(Structure):
    _fields_ = [
        ("cleaner", Cleaner),
        ("detectfunctions", DetectFunctions)
    ]

# Define the outermost structure for kotlin
class Kotlin(Structure):
    _fields_ = [
        ("root", Root),
    ]

class AngryData_ExportedSymbols(Structure):
    _fields_ = [
        ("DisposeStablePointer", DisposeStablePointerType),
        ("DisposeString", DisposeStringType),
        ("IsInstance", IsInstanceType),
        ("createNullableByte", createNullableByteType),
        ("getNonNullValueOfByte", getNonNullValueOfByteType),
        ("createNullableShort", createNullableShortType),
        ("getNonNullValueOfShort", getNonNullValueOfShortType),
        ("createNullableInt", createNullableIntType),
        ("getNonNullValueOfInt", getNonNullValueOfIntType),
        ("createNullableLong", createNullableLongType),
        ("getNonNullValueOfLong", getNonNullValueOfLongType),
        ("createNullableFloat", createNullableFloatType),
        ("getNonNullValueOfFloat", getNonNullValueOfFloatType),
        ("createNullableDouble", createNullableDoubleType),
        ("getNonNullValueOfDouble", getNonNullValueOfDoubleType),
        ("createNullableChar", createNullableCharType),
        ("getNonNullValueOfChar", getNonNullValueOfCharType),
        ("createNullableBoolean", createNullableBooleanType),
        ("getNonNullValueOfBoolean", getNonNullValueOfBooleanType),
        ("createNullableUnit", createNullableUnitType),
        ("createNullableUByte", createNullableUByteType),
        ("getNonNullValueOfUByte", getNonNullValueOfUByteType),
        ("createNullableUShort", createNullableUShortType),
        ("getNonNullValueOfUShort", getNonNullValueOfUShortType),
        ("createNullableUInt", createNullableUIntType),
        ("getNonNullValueOfUInt", getNonNullValueOfUIntType),
        ("createNullableULong", createNullableULongType),
        ("getNonNullValueOfULong", getNonNullValueOfULongType),

        ("kotlin", Kotlin),  # Define nested structures as needed
    ]

lib.AngryData_symbols.restype = POINTER(AngryData_ExportedSymbols)
symbols = lib.AngryData_symbols()

# Helper function to call functions
def call_detect_function(function_name, t):
    detect_functions = symbols.contents.kotlin.root.detectfunctions
    detect_func = getattr(detect_functions, function_name)
    detect_func.restype = AngryData_KInt
    return detect_func(t.encode('utf-8')).value

# Detect functions
def detect_account_number(t):
    return call_detect_function("detectAccountNumber", text)

def detect_address(text):
    return call_detect_function("detectAddress", text)

def detect_cvv(text):
    return call_detect_function("detectCVV", text)

def detect_car_number(text):
    return call_detect_function("detectCarNumber", text)

def detect_card_numbers(text):
    return call_detect_function("detectCardNumbers", text)

def detect_emails(text):
    return call_detect_function("detectEmails", text)

def detect_inn(text):
    return call_detect_function("detectINN", text)

def detect_ip(text):
    return call_detect_function("detectIP", text)

def detect_ipv6(text):
    return call_detect_function("detectIPv6", text)

def detect_login(text):
    return call_detect_function("detectLogin", text)

def detect_name(text):
    return call_detect_function("detectName", text)

def detect_oms(text):
    return call_detect_function("detectOMS", text)

def detect_passport(text):
    return call_detect_function("detectPassport", text)

def detect_password(text):
    return call_detect_function("detectPassword", text)

def detect_phones(text):
    return call_detect_function("detectPhones", text)

def detect_sinls(text):
    return call_detect_function("detectSINLS", text)

def detect_valuable_info(text):
    return call_detect_function("detectValuableInfo", text)

# Clean text function
def clean_text(text):
    clean_func = symbols.contents.kotlin.root.cleaner.cleanText
    clean_func.restype = c_char_p
    cleaned = clean_func(c_char_p(text.encode('utf-8')))
    return cleaned.decode('utf-8')

with open("../kotlin-lib/src/commonTest/resources/testFiles/testText.txt", encoding="utf-8") as f:
    text = f.read()
    print("Original text:", text)
    print("Cleaned text:", clean_text(text))
    print("Detected Account Number:", detect_passport(text))
    print("Detected Address:", detect_address(text))
    print("Detected CVV:", detect_cvv(text))
    print("Detected Car Number:", detect_car_number(text))
    print("Detected Card Numbers:", detect_card_numbers(text))
    print("Detected Emails:", detect_emails(text))
    print("Detected INN:", detect_inn(text))
    print("Detected IP:", detect_ip(text))
    print("Detected IPv6:", detect_ipv6(text))
    print("Detected Login:", detect_login(text))
    print("Detected Name:", detect_name(text))
    print("Detected OMS:", detect_oms(text))
    print("Detected Passport:", detect_passport(text))
    print("Detected Password:", detect_password(text))
    print("Detected Phones:", detect_phones(text))
    print("Detected SINLS:", detect_sinls(text))
    print("Detected Valuable Info:", detect_valuable_info(text))
