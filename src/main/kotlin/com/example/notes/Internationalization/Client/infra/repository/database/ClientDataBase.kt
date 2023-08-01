package com.example.notes.Internationalization.Client.infra.repository.database

import com.example.notes.Internationalization.Region.infra.repository.database.RegionDatabase
import org.jetbrains.exposed.sql.Table

object ClientDataBase : Table("client") {
    var uuid = uuid("uuid").uniqueIndex()
    var name = varchar("name", 250).nullable()
    var code = integer("code").uniqueIndex()
    var identifier = varchar("identifier", 250).nullable()
    var regionCode = reference("region_code", RegionDatabase.code).nullable()
    var situation = integer("situation_code")
    var description = text("description").nullable()
    var address = text("address").nullable()

    init {
        PrimaryKey(code)
    }
}