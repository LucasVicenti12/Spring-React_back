package com.example.notes.Internationalization.Region.infra.repository.implementation

import com.example.notes.Internationalization.Region.domain.entities.City
import com.example.notes.Internationalization.Region.domain.entities.Region
import com.example.notes.Internationalization.Region.domain.repository.RegionRepository
import com.example.notes.Internationalization.Region.infra.repository.database.CityDatabase
import com.example.notes.Internationalization.Region.infra.repository.database.RegionDatabase
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service
import java.util.*

@Service
class RegionRepositoryImplementation : RegionRepository {
    override fun createRegion(region: Region): Region? {
        transaction {
            RegionDatabase.insert {
                it[uuid] = region.uuid!!
                it[code] = region.code!!
                it[description] = region.description!!
            }
            region
        }
        region.city?.map {
            val city: City? = it
            city?.uuid = UUID.randomUUID()
            transaction {
                CityDatabase.insert {
                    it[uuid] = city?.uuid!!
                    it[code] = city?.code!!
                    it[description] = city?.description!!
                    it[cityUF] = city?.cityUF!!
                    it[regionUUID] = region.uuid!!
                }
            }
        }
        return region
    }

    override fun getRegionByUUID(regionUUID: UUID): Region? {
        return transaction {
            RegionDatabase.select( Op.build { RegionDatabase.uuid eq regionUUID } ).map {
                Region(
                    uuid = it[RegionDatabase.uuid],
                    description = it[RegionDatabase.description],
                    code = it[RegionDatabase.code],
                    city = getCitiesByRegionUUID(it[RegionDatabase.uuid])
                )
            }.firstOrNull()
        }
    }

    override fun updateRegion(region: Region): Region? {
        transaction {
            RegionDatabase.update({
                RegionDatabase.uuid eq region.uuid!!
            }) {
                it[description] = region.description!!
            }
            region
        }
        deleteAllCitiesByRegionUUID(region.uuid!!)
        region.city?.map {
            val city: City? = it
            city?.uuid = UUID.randomUUID()
            transaction {
                CityDatabase.insert {
                    it[uuid] = city?.uuid!!
                    it[code] = city?.code!!
                    it[description] = city?.description!!
                    it[cityUF] = city?.cityUF!!
                    it[regionUUID] = region.uuid!!
                }
            }
        }
        return region
    }

    override fun listAllRegions(): List<Region> {
        return transaction {
            RegionDatabase
                .selectAll().map {
                    Region(
                        uuid = it[RegionDatabase.uuid],
                        description = it[RegionDatabase.description],
                        code = it[RegionDatabase.code],
                        city = getCitiesByRegionUUID(it[RegionDatabase.uuid])
                    )
                }
        }
    }

    fun getCitiesByRegionUUID(regionUUID: UUID): List<City> {
        return transaction {
            CityDatabase
                .select(
                    Op.build { CityDatabase.regionUUID eq regionUUID }
                ).map {
                    City(
                        uuid = it[CityDatabase.uuid],
                        description = it[CityDatabase.description],
                        cityUF = it[CityDatabase.cityUF],
                        code = it[CityDatabase.code]
                    )
                }
        }
    }

    fun deleteAllCitiesByRegionUUID(regionUUID: UUID){
        transaction {
            CityDatabase.deleteWhere { Op.build { CityDatabase.regionUUID greaterEq regionUUID } }
        }
    }
}