package com.shepeliev.ksdp.parsers

internal object Phone : FieldParser<String> by StringFieldParser('p')