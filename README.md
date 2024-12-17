# A Kotlin Multiplatform Session Description (SDP) Parser

A lightweight Session Description Protocol (SDP) parser written in Kotlin Multiplatform. 
**ksdp** allows you to parse, manipulate, and generate SDP messages, adhering to [RFC 2327](https://datatracker.ietf.org/doc/html/rfc2327).

## Installation

Add the dependency to your project using your build system:

### Gradle Kotlin DSL
```kotlin
dependencies {
    implementation("com.shepeliev.ksdp:ksdp:<version>")
}
```

### Gradle Groovy DSL
```groovy
dependencies {
    implementation 'com.shepeliev.ksdp:ksdp:<version>'
}
```

### Multiplatform
Runs on JVM, Android, iOS, macOS, JS, WasmJS.

## Usage

### Parsing an SDP message
```kotlin
val sdpMessage = """
v=0
o=jdoe 2890844526 2890842807 IN IP4 10.47.16.5
s=SDP Seminar
c=IN IP4 224.2.17.12/127
t=2873397496 2873404696
m=audio 49170 RTP/AVP 0
a=recvonly
"""

val sessionDescription = sdpMessage.parseSdp()
```

### Generating an SDP message
```kotlin
val sessionDescription = SessionDescription(
    version = 0,
    origin = Origin("jdoe", sessionId = 2890844526, sessionVersion = 2890842807, address = "10.47.16.5"),
    sessionName = "SDP Seminar",
    connection = Connection("224.2.17.12/127"),
    time = mutableListOf(
        TimeDescription(
            time = Time(Instant.fromEpochSeconds(825434819), Instant.fromEpochSeconds(833473619))
        )
    ),
    mediaDescriptions = mutableListOf(
        MediaDescription(
            media = Media("audio", 53710, "RTP/SAVPF", mutableListOf("111", "8", "0")),
            attributes = mutableListOf(Attribute.Identity("recvonly"))
        )
    )
)
println(sessionDescription.sdp())
```
