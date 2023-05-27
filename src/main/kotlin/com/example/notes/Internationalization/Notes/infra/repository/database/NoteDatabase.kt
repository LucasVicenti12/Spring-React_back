package com.example.notes.Internationalization.Notes.infra.repository.database

import org.jetbrains.exposed.sql.Table


object NoteDatabase : Table("notes"){
    var uuid = uuid("uuid").uniqueIndex()
    var title = varchar("title", 200)
    var description = text("description").nullable()
}