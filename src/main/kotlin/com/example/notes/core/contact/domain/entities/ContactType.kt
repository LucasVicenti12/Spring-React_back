package com.example.notes.core.contact.domain.entities

import java.util.UUID

class ContactType(
    var uuid: UUID?,
    var label: String? = "",
    var code: Int? = 0
) {
    constructor() : this(uuid = null)
}