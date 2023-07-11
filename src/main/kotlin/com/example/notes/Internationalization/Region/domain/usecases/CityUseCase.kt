package com.example.notes.Internationalization.Region.domain.usecases

import com.example.notes.Internationalization.Region.domain.entities.City
import com.example.notes.Internationalization.Region.domain.usecases.response.CityArrayResponse
import com.example.notes.Internationalization.Region.domain.usecases.response.CityResponse
import java.util.*

interface CityUseCase {
    fun createCity(city: City): CityResponse
    fun getCityByUUID(cityUUID: UUID): CityResponse
    fun updateCity(city: City): CityResponse
    fun listAllCities() : CityArrayResponse
}