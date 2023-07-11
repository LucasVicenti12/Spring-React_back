package com.example.notes.Internationalization.Region.domain.exceptions

import com.example.notes.core.shared.error.GenericError

val REGION_GENERIC_ERROR = RegionException("REGION_GENERIC_ERROR", "An unexpected error has ocurred!")
val REGION_DESCRIPTION_ERROR = RegionException("REGION_DESCRIPTION_ERROR", "You need to inform the region description!")
val REGION_CODE_ALREADY_EXISTS = RegionException("REGION_CODE_ALREADY_EXISTS", "The code region already exists!")
val REGION_CITY_ERROR = RegionException("REGION_CITY_ERROR", "Inform at least one city!")
val REGION_ISNT_EXISTS = RegionException("REGION_ISNT_EXISTS", "The region isn't exists!")

class RegionException (code : String, description : String) : GenericError("region-module", code, description)