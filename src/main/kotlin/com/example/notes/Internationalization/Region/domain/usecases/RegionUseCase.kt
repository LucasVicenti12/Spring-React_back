package com.example.notes.Internationalization.Region.domain.usecases

import com.example.notes.Internationalization.Region.domain.entities.Region
import com.example.notes.Internationalization.Region.domain.usecases.response.RegionArrayResponse
import com.example.notes.Internationalization.Region.domain.usecases.response.RegionResponse
import java.util.*

interface RegionUseCase {
    fun createRegion(region : Region) : RegionResponse
    fun getRegionByUUID(regionUUID: UUID): RegionResponse
    fun updateRegion(region: Region): RegionResponse
    fun listAllRegions() : RegionArrayResponse
}