package com.shepeliev.ksdp.parsers

internal object EmailParser : Parser<String> by StringParser('e')