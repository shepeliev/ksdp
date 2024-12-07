package com.shepeliev.ksdp.uri

data class Authority(
    val userInfo: String?,
    val host: String,
    val port: Int?
) {
//    companion object : Grammar<Authority>() {

//    }
}