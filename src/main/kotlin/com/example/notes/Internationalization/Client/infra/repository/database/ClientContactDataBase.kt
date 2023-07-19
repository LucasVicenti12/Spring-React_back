package com.example.notes.Internationalization.Client.infra.repository.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object ClientContactDataBase : Table("client_contact"){
    var uuid = uuid("uuid")
    var contactTypeCode = integer("contact_type_code")
    var description = varchar("description", 250)
    var createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    var clientCode = reference("client_code", ClientDataBase.code)
}