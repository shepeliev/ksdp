package com.shepeliev.ksdp

actual fun readSdpFile(name: String): String {
    return TestUtils::class.java.getResource("/$name.sdp")?.readText() ?: error("Resource not found: $name.sdp")
}

private object TestUtils
