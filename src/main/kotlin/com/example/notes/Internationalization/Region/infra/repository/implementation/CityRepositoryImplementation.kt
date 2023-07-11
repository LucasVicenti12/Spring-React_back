package com.example.notes.Internationalization.Region.infra.repository.implementation

import com.example.notes.Internationalization.Region.domain.entities.City
import com.example.notes.Internationalization.Region.domain.repository.CityRepository
import com.example.notes.Internationalization.Region.infra.repository.database.CityDatabase
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Service
import java.util.*

@Service
class CityRepositoryImplementation : CityRepository {
    override fun createCity(city: City): City? {
        transaction {
            CityDatabase.insert {
                it[uuid] = city.uuid!!
                it[description] = city.description!!
                it[code] = city.code!!
                it[cityUF] = city.cityUF!!
            }
            city
        }
        return city
    }

    override fun getCityByUUID(cityUUID: UUID): City? {
        return transaction {
            CityDatabase.select { CityDatabase.uuid eq cityUUID }.map {
                City(
                    uuid = it[CityDatabase.uuid],
                    description = it[CityDatabase.description],
                    code = it[CityDatabase.code],
                    cityUF = it[CityDatabase.cityUF]
                )
            }.firstOrNull()
        }
    }

    override fun updateCity(city: City): City? {
        transaction {
            CityDatabase.update({
                CityDatabase.uuid eq city.uuid!!
            }) {
                it[description] = city.description!!
            }
            city
        }
        return city
    }

    override fun getCityByCode(cityCODE: Int): City? {
        return transaction {
            CityDatabase.select { CityDatabase.code eq cityCODE }.map {
                City(
                    uuid = it[CityDatabase.uuid],
                    description = it[CityDatabase.description],
                    code = it[CityDatabase.code],
                    cityUF = it[CityDatabase.cityUF]
                )
            }.firstOrNull()
        }
    }

    override fun listAllCities(): List<City> {
        return transaction {
            val result = CityDatabase
                .slice(CityDatabase.uuid, CityDatabase.code, CityDatabase.description, CityDatabase.cityUF, CityDatabase.regionUUID)
                .selectAll()
            result.map {
                City(
                    uuid = it[CityDatabase.uuid],
                    description = it[CityDatabase.description],
                    code = it[CityDatabase.code],
                    cityUF = it[CityDatabase.cityUF],
                    regionUUID = it[CityDatabase.regionUUID]
                )
            }
        }
    }

}