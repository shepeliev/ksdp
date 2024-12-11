package com.shepeliev.ksdp.parsers

internal object SessionNameParser : FieldParser<String> by StringFieldParser('s')