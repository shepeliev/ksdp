package com.shepeliev.ksdp

public data class Origin(
    val username: String,
    val sessionId: Long,
    val sessionVersion: Long,
    val address: String,
    val networkType: String = "IN",
    val addressType: String = "IP4",
) {
    override fun toString(): String = "o=$username $sessionId $sessionVersion $networkType $addressType $address"
}
