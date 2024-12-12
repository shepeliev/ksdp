package com.shepeliev.ksdp

/**
 * A Connection represents the c= field associated with a SessionDescription or with an individual
 * MediaDescription and is used to identify a network address on which media can be received.
 *
 * The Connection in the SessionDescription applies to all MediaDescriptions unless a
 * MediaDescription specifically overrides it. The Connection identifies the network type (IN for internet),
 * address type (IP4 or IP6), the start of an address range, the time to live of the session and the number of
 * addresses in the range. Both the time to live and number of addresses are optional.
 *
 * A Connection could therefore be of one these forms:
 *
 *     c=IN IP4 myhost.somewhere.com (no ttl and only one address)
 *     c=IN IP4 myhost.somewhere.com/5 (a ttl of 5)
 *     c=IN IP4 myhost.somewhere.com/5/2 (a ttl of 5 and 2 addresses)
 *
 * This implementation does not explicitly support ttl and number of addresses.
 *
 * Please refer to IETF RFC 2327 for a description of SDP.
 */
public data class Connection(
    var address: String,
    var addressType: String = "IP4",
    var networkType: String = "IN",
)

internal val Connection.line: String get() = "c=$networkType $addressType $address"
