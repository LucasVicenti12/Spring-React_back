package com.example.notes.Internationalization.Region.domain.entities

import java.util.UUID

class City(
    var uuid: UUID?,
    var description: String? = "",
    var cityUF: String? = "",
    var regionUUID: UUID? = null,
    var code: Int? = 0
) {
    constructor() : this(uuid = null)
}