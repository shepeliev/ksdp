package com.shepeliev.ksdp

import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.stringWithContentsOfFile

actual fun readSdpFile(name: String): String {
    val path = NSBundle.mainBundle.pathForResource("$name.sdp", null) ?: error("File $name.sdp not found")
    return NSString.stringWithContentsOfFile(path) as? String ?: error("Could not open file at $path")
}
