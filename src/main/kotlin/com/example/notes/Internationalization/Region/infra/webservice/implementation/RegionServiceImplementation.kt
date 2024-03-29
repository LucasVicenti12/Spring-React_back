package com.example.notes.Internationalization.Region.infra.webservice.implementation

import com.example.notes.Internationalization.Region.domain.entities.Region
import com.example.notes.Internationalization.Region.domain.usecases.RegionUseCase
import com.example.notes.Internationalization.Region.domain.usecases.response.RegionArrayResponse
import com.example.notes.Internationalization.Region.domain.usecases.response.RegionResponse
import com.example.notes.Internationalization.Region.infra.webservice.RegionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/region")
class RegionServiceImplementation (private val regionUseCase: RegionUseCase) : RegionService {
    @PostMapping
    override fun createRegion(@RequestBody region: Region): RegionResponse {
        return regionUseCase.createRegion(region)
    }

    override fun getRegionByUUID(regionUUID: UUID): RegionResponse {
        TODO("Not yet implemented")
    }

    override fun updateRegion(region: Region): RegionResponse {
        TODO("Not yet implemented")
    }

    @GetMapping
    override fun listAllRegions(): RegionArrayResponse {
        return regionUseCase.listAllRegions()
    }
}