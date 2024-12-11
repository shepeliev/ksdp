package com.shepeliev.ksdp.parsers

internal object Email : FieldParser<String> by StringFieldParser('e')