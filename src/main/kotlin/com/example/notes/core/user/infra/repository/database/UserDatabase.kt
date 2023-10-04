package com.example.notes.core.user.infra.repository.database

import org.jetbrains.exposed.sql.Table

object UserDatabase : Table("user"){
    var uuid = uuid("uuid").uniqueIndex()
    var code = integer("code").uniqueIndex().autoIncrement()
    var name = varchar("name", 250)
    var email = varchar("email", 100)
    var username = varchar("username", 100)
    var password = text("password")
}