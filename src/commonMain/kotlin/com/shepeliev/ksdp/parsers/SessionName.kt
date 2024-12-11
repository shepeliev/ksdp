package com.shepeliev.ksdp.parsers

internal object SessionName : FieldParser<String> by StringFieldParser('s')