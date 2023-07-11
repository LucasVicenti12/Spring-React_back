package com.example.notes.Internationalization.Region.domain.repository

import com.example.notes.Internationalization.Region.domain.entities.City
import java.util.UUID

interface CityRepository {
    fun createCity(city: City): City?
    fun getCityByUUID(cityUUID: UUID): City?
    fun updateCity(city: City): City?
    fun getCityByCode(cityCODE : Int) : City?
    fun listAllCities() : List<City>
}