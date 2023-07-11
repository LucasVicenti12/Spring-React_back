package com.example.notes.Internationalization.Region.domain.repository

import com.example.notes.Internationalization.Region.domain.entities.City
import com.example.notes.Internationalization.Region.domain.entities.Region
import java.util.*

interface RegionRepository {
    fun createRegion(region : Region) : Region?
    fun getRegionByUUID(regionUUID: UUID): Region?
    fun updateRegion(region: Region): Region?
    fun listAllRegions() : List<Region>
}