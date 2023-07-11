package com.example.notes.Internationalization.Region.infra.repository.database

import org.jetbrains.exposed.sql.Table

object CityDatabase : Table("city"){
    var uuid = uuid("uuid").uniqueIndex()
    var description = text("description").nullable()
    var cityUF = text("city_uf").nullable()
    var regionUUID = reference("region_uuid", RegionDatabase.uuid).nullable()
    var code = integer("code")
}