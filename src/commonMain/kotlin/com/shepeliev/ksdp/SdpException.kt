package com.shepeliev.ksdp

public class SdpException(message: String) : Exception(message)

internal inline fun checkIt(condition: Boolean, message: () -> String) {
    if (!condition) throw SdpException(message())
}