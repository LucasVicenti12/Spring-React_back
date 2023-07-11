package com.example.notes.core.contact.infra.repository.database

import org.jetbrains.exposed.sql.Table

object ContactTypeDatabase : Table("contact_type") {
    var uuid = uuid("contact_uuid").uniqueIndex()
    var label = varchar("label", 250).nullable()
    var code = integer("contact_code")
}