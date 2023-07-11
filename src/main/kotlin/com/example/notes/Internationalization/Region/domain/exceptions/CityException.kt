package com.example.notes.Internationalization.Region.domain.exceptions

import com.example.notes.core.shared.error.GenericError

val CITY_GENERIC_ERROR = CityException("CITY_GENERIC_ERROR", "An unexpected error has ocurred!")
val CITY_DESCRIPTION_ERROR = CityException("CITY_DESCRIPTION_ERROR", "Inform the city description!")
val CITY_CODE_ALREADY_EXISTS = CityException("CITY_CODE_ALREADY_EXISTS", "The code region already exists!")
val CITY_INVALID_CODE = CityException("CITY_INVALID_CODE", "The code is invalid!")
val CITY_ISNT_EXISTS = CityException("CITY_ISNT_EXISTS", "The city isn't exists!")
val CITY_INVALID_UF = CityException("CITY_INVALID_UF", "The UF is invalid!")


class CityException(code : String, description: String) : GenericError("city-module", code, description)