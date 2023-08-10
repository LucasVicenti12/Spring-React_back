package com.example.notes.Internationalization.Region.domain.usecases.implementation

import com.example.notes.Internationalization.Region.domain.entities.Region
import com.example.notes.Internationalization.Region.domain.exceptions.REGION_CITY_ERROR
import com.example.notes.Internationalization.Region.domain.exceptions.REGION_DESCRIPTION_ERROR
import com.example.notes.Internationalization.Region.domain.exceptions.REGION_GENERIC_ERROR
import com.example.notes.Internationalization.Region.domain.exceptions.REGION_ISNT_EXISTS
import com.example.notes.Internationalization.Region.domain.repository.RegionRepository
import com.example.notes.Internationalization.Region.domain.usecases.RegionUseCase
import com.example.notes.Internationalization.Region.domain.usecases.response.RegionArrayResponse
import com.example.notes.Internationalization.Region.domain.usecases.response.RegionResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class RegionUseCaseImplementation(private val regionRepository: RegionRepository) : RegionUseCase{
    companion object{
        private val logger = LoggerFactory.getLogger(RegionUseCaseImplementation::class.java)
    }

    override fun createRegion(region: Region): RegionResponse {
        return try{
            if(region.description == ""){
                return RegionResponse(error = REGION_DESCRIPTION_ERROR)
            }
            if(region.city?.size ?: Int  == 0){
                return RegionResponse(error = REGION_CITY_ERROR)
            }
            region.uuid = UUID.randomUUID()
            RegionResponse(region = regionRepository.createRegion(region), error = null)
        }catch (e : Exception){
            logger.error("REGION", e)
            return RegionResponse(error = REGION_GENERIC_ERROR)
        }
    }

    override fun getRegionByUUID(regionUUID: UUID): RegionResponse {
        return try {
            return RegionResponse(region = regionRepository.getRegionByUUID(regionUUID), error = null)
        }catch (e : Exception){
            logger.error("REGION", e)
            return RegionResponse(error = REGION_ISNT_EXISTS)
        }
    }

    override fun updateRegion(region: Region): RegionResponse {
        return try{
            if(region.description == ""){
                return RegionResponse(error = REGION_DESCRIPTION_ERROR)
            }
            if(region.city?.size ?: Int  == 0){
                return RegionResponse(error = REGION_CITY_ERROR)
            }
            RegionResponse(region = regionRepository.updateRegion(region), error = null)
        }catch (e : Exception){
            logger.error("REGION", e)
            return RegionResponse(error = REGION_GENERIC_ERROR)
        }
    }

    override fun listAllRegions(): RegionArrayResponse {
        return try {
            return RegionArrayResponse(regions = regionRepository.listAllRegions(), error = null)
        }catch (e : Exception){
            logger.error("REGION", e)
            return RegionArrayResponse(error = REGION_GENERIC_ERROR)
        }
    }
}