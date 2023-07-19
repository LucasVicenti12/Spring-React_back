package com.example.notes.Internationalization.Region.infra.repository.database

import org.jetbrains.exposed.sql.Table

object RegionDatabase : Table("region"){
    var uuid = uuid("uuid").uniqueIndex()
    var description = text("description").nullable()
    var code = integer("code").uniqueIndex()

    init {
        PrimaryKey(code)
    }
}