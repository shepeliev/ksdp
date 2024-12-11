package com.shepeliev.ksdp.parsers

internal object EmailParser : FieldParser<String> by StringFieldParser('e')