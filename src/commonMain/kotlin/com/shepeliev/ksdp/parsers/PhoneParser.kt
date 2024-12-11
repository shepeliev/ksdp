package com.shepeliev.ksdp.parsers

internal object PhoneParser : FieldParser<String> by StringFieldParser('p')