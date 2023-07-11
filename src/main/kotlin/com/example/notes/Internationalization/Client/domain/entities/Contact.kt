package com.example.notes.Internationalization.Client.domain.entities

import com.example.notes.core.contact.domain.entities.ContactType
import java.time.LocalDateTime
import java.util.*

class Contact(
    var uuid: UUID?,
    var contactTypeCode: Int? = 0,
    var description: String? = "",
    var createdAt: LocalDateTime? = LocalDateTime.now(),
    var clientCode: Int? = 0,
    var contactType: ContactType? = null
) {
    constructor() : this(uuid = null)
}