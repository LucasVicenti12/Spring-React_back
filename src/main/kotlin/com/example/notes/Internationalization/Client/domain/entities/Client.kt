package com.example.notes.Internationalization.Client.domain.entities

import java.util.UUID

class Client(
    var uuid: UUID?,
    var name: String? = "",
    var code: Int? = 0,
    var identifier: String? = "",
    var regionCode: Int? = 0,
    var situation: Int? = 0,
    var description: String? = "",
    var address: String? = "",
    var contacts: List<Contact>? = listOf()
) {
    constructor() : this(uuid = null)
}