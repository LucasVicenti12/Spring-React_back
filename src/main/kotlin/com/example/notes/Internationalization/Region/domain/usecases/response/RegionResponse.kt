package com.example.notes.Internationalization.Region.domain.usecases.response

import com.example.notes.Internationalization.Region.domain.entities.Region
import com.example.notes.Internationalization.Region.domain.exceptions.RegionException

data class RegionResponse(val region: Region? = null, val error: RegionException? = null)

data class RegionArrayResponse(val regions: List<Region>? = null, val error: RegionException? = null)