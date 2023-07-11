package com.example.notes.Internationalization.Region.domain.usecases.response

import com.example.notes.Internationalization.Region.domain.entities.City
import com.example.notes.Internationalization.Region.domain.exceptions.CityException

data class CityResponse(val city: City? = null, val erro: CityException? = null)

data class CityArrayResponse(val cities: List<City>? = null, val erro: CityException? = null)