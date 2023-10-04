package com.example.notes.core.user.domain.entity

import java.util.UUID

class User(
    var uuid: UUID? = null,
    var code: Int? = null,
    var name: String? = null,
    var email: String? = null,
    var username: String? = null,
    var password: String? = null
) {
    constructor() : this(uuid = null)
}