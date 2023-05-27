package com.example.notes.Internationalization.Notes.domain.entities

import java.util.UUID

open class Note (
    var uuid: UUID?,
    var title: String? = "",
    var description: String? = ""
){
    constructor() : this(uuid = null)
}
