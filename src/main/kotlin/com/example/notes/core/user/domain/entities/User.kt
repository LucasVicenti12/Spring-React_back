package com.example.notes.core.user.domain.entities

import java.util.UUID

class User(
    var uuid: UUID?,
    var name: String? = "",
    var email: String? = "",
    var authRecord: String? = "",
    var password: String? = "",
) {
    constructor() : this(uuid = null)
}