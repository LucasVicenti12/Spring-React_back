package com.example.notes.core.user.domain.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.UUID

@JsonIgnoreProperties(value = ["passwordHash"])
data class User(
    var uuid: UUID?,
    var name: String? = "",
    var email: String? = "",
    var authRecord: String? = "",
    var password: String? = "",
    var passwordHash: String? = null
) {
    constructor() : this(uuid = null)
}