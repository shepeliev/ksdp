package com.shepeliev.ksdp

val TEST_PARSE_SDP = """v=0\r
o=jdoe 2890844526 2890842807 IN IP4 10.47.16.5\r
s=SDP Seminar\r
i=A Seminar on the session description protocol\r
u=http://www.example.com/seminars/sdp.pdf\r
e=j.doe@example.com (Jane Doe)\r
p=+1 617 555-5555\r
c=IN IP4 224.2.17.12/127\r
b=AS:30\r
b=RS:30\r
t=3034423619 3042462419\r
r=7d 1h 0 25h\r
r=1d 30m 10s 25h\r
z=3034423619 -1h 3042462419 0\r
k=prompt\r
a=group:BUNDLE 0 1\r
a=extmap-allow-mixed\r
m=audio 53710/2 RTP/SAVPF 111 8 0\r
i=Audio title\r
c=IN IP4 224.2.17.12/127\r
b=AS:30\r
b=RS:30\r
k=clear:password\r
a=rtpmap:111 opus/48000/2\r
a=rtpmap:8 PCMA/8000\r
a=fmtp:111 minptime=10;useinbandfec=1\r
a=rtcp:53711\r
a=rtcp-fb:111 transport-cc\r
a=setup:actpass\r
a=ptime:20\r
a=sendrecv\r
m=video 50042 RTP/SAVPF 96 39\r
a=rtpmap:96 VP8/90000\r
a=rtpmap:39 H264/90000\r
a=inactive\r
""".replace("\\r", "\r")

val TEST_TO_STRING_SDP = """v=0\r
o=jdoe 2890844526 2890842807 IN IP4 10.47.16.5\r
s=SDP Seminar\r
i=A Seminar on the session description protocol\r
u=http://www.example.com/seminars/sdp.pdf\r
e=j.doe@example.com (Jane Doe)\r
p=+1 617 555-5555\r
c=IN IP4 224.2.17.12/127\r
b=AS:30\r
b=RS:30\r
t=3034423619 3042462419\r
r=604800 3600 0 90000\r
r=86400 1800 10 90000\r
z=3034423619 -3600 3042462419 0\r
k=clear:password\r
a=group:BUNDLE 0 1\r
a=extmap-allow-mixed\r
m=audio 53710/2 RTP/SAVPF 111 8 0\r
i=Audio title\r
c=IN IP4 224.2.17.12/127\r
b=AS:30\r
b=RS:30\r
k=clear:password\r
a=rtpmap:111 opus/48000/2\r
a=rtpmap:8 PCMA/8000\r
a=fmtp:111 minptime=10;useinbandfec=1\r
a=rtcp:53711\r
a=rtcp-fb:111 transport-cc\r
a=setup:actpass\r
a=ptime:20\r
a=sendrecv\r
m=video 50042 RTP/SAVPF 96 39\r
a=rtpmap:96 VP8/90000\r
a=rtpmap:39 H264/90000\r
a=inactive\r
""".replace("\\r", "\r")
