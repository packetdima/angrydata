import ctypes

angrydata = ctypes.CDLL("../library/build/bin/native/releaseShared/AngryData.dll")

# Define the necessary types
class AngryData_KNativePtr(ctypes.Structure):
    pass  # Define the structure as needed

class AngryData_KType(ctypes.Structure):
    pass  # Define the structure as needed

class AngryData_KBoolean(ctypes.c_bool):
    pass  # Assuming it's an integer type

class AngryData_KByte(ctypes.c_byte):
    pass  # Assuming it's a byte type

class AngryData_KShort(ctypes.c_short):
    pass  # Assuming it's a short type

class AngryData_KInt(ctypes.c_int):
    pass  # Assuming it's an int type

class AngryData_KLong(ctypes.c_long):
    pass  # Assuming it's a long type

class AngryData_KFloat(ctypes.c_float):
    pass  # Assuming it's a float type

class AngryData_KDouble(ctypes.c_double):
    pass  # Assuming it's a double type

class AngryData_KChar(ctypes.c_char):
    pass  # Assuming it's a wchar type

class AngryData_KUByte(ctypes.c_ubyte):
    pass  # Assuming it's an unsigned byte type

class AngryData_KUShort(ctypes.c_ushort):
    pass  # Assuming it's an unsigned short type

class AngryData_KUInt(ctypes.c_uint):
    pass  # Assuming it's an unsigned int type

class AngryData_KULong(ctypes.c_ulong):
    pass  # Assuming it's an unsigned long type

# Define function pointer types
DisposeStablePointerType = ctypes.CFUNCTYPE(None, AngryData_KNativePtr)
DisposeStringType = ctypes.CFUNCTYPE(None, ctypes.c_char_p)
IsInstanceType = ctypes.CFUNCTYPE(AngryData_KBoolean, AngryData_KNativePtr, ctypes.POINTER(AngryData_KType))

createNullableByteType = ctypes.CFUNCTYPE(ctypes.POINTER(AngryData_KByte), AngryData_KByte)
getNonNullValueOfByteType = ctypes.CFUNCTYPE(AngryData_KByte, ctypes.POINTER(AngryData_KByte))

createNullableShortType = ctypes.CFUNCTYPE(ctypes.POINTER(AngryData_KShort), AngryData_KShort)
getNonNullValueOfShortType = ctypes.CFUNCTYPE(AngryData_KShort, ctypes.POINTER(AngryData_KShort))

createNullableIntType = ctypes.CFUNCTYPE(ctypes.POINTER(AngryData_KInt), AngryData_KInt)
getNonNullValueOfIntType = ctypes.CFUNCTYPE(AngryData_KInt, ctypes.POINTER(AngryData_KInt))

createNullableLongType = ctypes.CFUNCTYPE(ctypes.POINTER(AngryData_KLong), AngryData_KLong)
getNonNullValueOfLongType = ctypes.CFUNCTYPE(AngryData_KLong, ctypes.POINTER(AngryData_KLong))

createNullableFloatType = ctypes.CFUNCTYPE(ctypes.POINTER(AngryData_KFloat), AngryData_KFloat)
getNonNullValueOfFloatType = ctypes.CFUNCTYPE(AngryData_KFloat, ctypes.POINTER(AngryData_KFloat))

createNullableDoubleType = ctypes.CFUNCTYPE(ctypes.POINTER(AngryData_KDouble), AngryData_KDouble)
getNonNullValueOfDoubleType = ctypes.CFUNCTYPE(AngryData_KDouble, ctypes.POINTER(AngryData_KDouble))

createNullableCharType = ctypes.CFUNCTYPE(ctypes.POINTER(AngryData_KChar), AngryData_KChar)
getNonNullValueOfCharType = ctypes.CFUNCTYPE(AngryData_KChar, ctypes.POINTER(AngryData_KChar))

createNullableBooleanType = ctypes.CFUNCTYPE(ctypes.POINTER(AngryData_KBoolean), AngryData_KBoolean)
getNonNullValueOfBooleanType = ctypes.CFUNCTYPE(AngryData_KBoolean, ctypes.POINTER(AngryData_KBoolean))

createNullableUnitType = ctypes.CFUNCTYPE(ctypes.POINTER(None))  # Assuming it returns void

createNullableUByteType = ctypes.CFUNCTYPE(ctypes.POINTER(AngryData_KUByte), AngryData_KUByte)
getNonNullValueOfUByteType = ctypes.CFUNCTYPE(AngryData_KUByte, ctypes.POINTER(AngryData_KUByte))

createNullableUShortType = ctypes.CFUNCTYPE(ctypes.POINTER(AngryData_KUShort), AngryData_KUShort)
getNonNullValueOfUShortType = ctypes.CFUNCTYPE(AngryData_KUShort, ctypes.POINTER(AngryData_KUShort))

createNullableUIntType = ctypes.CFUNCTYPE(ctypes.POINTER(AngryData_KUInt), AngryData_KUInt)
getNonNullValueOfUIntType = ctypes.CFUNCTYPE(AngryData_KUInt, ctypes.POINTER(AngryData_KUInt))

createNullableULongType = ctypes.CFUNCTYPE(ctypes.POINTER(AngryData_KULong), AngryData_KULong)
getNonNullValueOfULongType = ctypes.CFUNCTYPE(AngryData_KULong, ctypes.POINTER(AngryData_KULong))

class AngryData(ctypes.Structure):
    _fields_ = [
        ("DetectEmail", ctypes.CFUNCTYPE(AngryData_KInt, ctypes.c_char_p)),
    ]

# Define the next level structure for packetdima
class PacketDima(ctypes.Structure):
    _fields_ = [
        ("angrydata", AngryData),
    ]

# Define the structure for ru
class Ru(ctypes.Structure):
    _fields_ = [
        ("packetdima", PacketDima),
    ]

# Define the root structure
class Root(ctypes.Structure):
    _fields_ = [
        ("ru", Ru),
    ]

# Define the outermost structure for kotlin
class Kotlin(ctypes.Structure):
    _fields_ = [
        ("root", Root),
    ]

class AngryData_ExportedSymbols(ctypes.Structure):
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

# Access the exported symbols
AngryData_symbols = angrydata.AngryData_symbols
AngryData_symbols.restype = ctypes.POINTER(AngryData_ExportedSymbols)

# Get the exported symbols
symbols = AngryData_symbols()


# Example usage of the DetectEmail function
email_text = "example@mail.com"  # Ensure the string is in bytes
detect_email_func = symbols.contents.kotlin.root.ru.packetdima.angrydata.DetectEmail

# Call the function
result = detect_email_func(email_text.encode("utf-8"))

value = getNonNullValueOfIntType(result.value)
print("DetectEmail result for string", "\"" + email_text + "\"", "is:", result.value)