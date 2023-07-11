package com.example.notes.Internationalization.Region.domain.entities

import java.util.UUID

open class Region(
    var uuid: UUID?,
    var description: String? = "",
    var code: Int? = 0,
    var city: List<City>? = listOf()
    ) {
    constructor() : this(uuid = null)
}