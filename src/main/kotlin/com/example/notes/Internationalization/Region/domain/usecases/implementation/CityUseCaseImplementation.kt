package com.example.notes.Internationalization.Region.domain.usecases.implementation

import com.example.notes.Internationalization.Region.domain.entities.City
import com.example.notes.Internationalization.Region.domain.exceptions.*
import com.example.notes.Internationalization.Region.domain.repository.CityRepository
import com.example.notes.Internationalization.Region.domain.usecases.CityUseCase
import com.example.notes.Internationalization.Region.domain.usecases.response.CityArrayResponse
import com.example.notes.Internationalization.Region.domain.usecases.response.CityResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class CityUseCaseImplementation(private val cityRepository: CityRepository) : CityUseCase {
    companion object {
        private val logger = LoggerFactory.getLogger(CityUseCaseImplementation::class.java)
    }

    override fun createCity(city: City): CityResponse {
        return try {
            if (city.description == "") {
                return CityResponse(erro = CITY_DESCRIPTION_ERROR)
            }
            if (city.code == 0) {
                return CityResponse(erro = CITY_INVALID_CODE)
            } else if (cityRepository.getCityByCode(city.code!!) != null) {
                return CityResponse(erro = CITY_CODE_ALREADY_EXISTS)
            }
            if(city.cityUF == ""){
                return CityResponse(erro = CITY_INVALID_UF)
            }
            city.uuid = UUID.randomUUID()
            CityResponse(city = cityRepository.createCity(city), erro = null)
        } catch (e: Exception) {
            logger.error("CITY", e)
            CityResponse(erro = CITY_GENERIC_ERROR)
        }
    }

    override fun getCityByUUID(cityUUID: UUID): CityResponse {
        return try {
            return CityResponse(city = cityRepository.getCityByUUID(cityUUID), erro = null)
        } catch (e: Exception) {
            logger.error("CITY", e)
            return CityResponse(erro = CITY_ISNT_EXISTS)
        }
    }

    override fun updateCity(city: City): CityResponse {
        return try {
            if (city.description == "") {
                return CityResponse(erro = CITY_DESCRIPTION_ERROR)
            }
            CityResponse(city = cityRepository.updateCity(city), erro = null)
        } catch (e: Exception) {
            logger.error("CITY", e)
            CityResponse(erro = CITY_GENERIC_ERROR)
        }
    }

    override fun listAllCities(): CityArrayResponse {
        return try {
            return CityArrayResponse(cities = cityRepository.listAllCities(), erro = null)
        } catch (e: Exception) {
            logger.error("CITY", e)
            return CityArrayResponse(erro = CITY_GENERIC_ERROR)
        }
    }

}