package com.example.notes.core.user.infra.repository.database

import org.jetbrains.exposed.sql.Table

object UserDatabase : Table("user") {
    var uuid = uuid("uuid").uniqueIndex()
    var name = text("name")
    var email = varchar("email", 100)
    var authRecord = varchar("auth_record", 100)
    var password = text("password")
}