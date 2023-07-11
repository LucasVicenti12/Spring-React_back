package com.example.notes.Internationalization.Region.infra.webservice.implementation

import com.example.notes.Internationalization.Region.domain.entities.City
import com.example.notes.Internationalization.Region.domain.usecases.CityUseCase
import com.example.notes.Internationalization.Region.domain.usecases.response.CityArrayResponse
import com.example.notes.Internationalization.Region.domain.usecases.response.CityResponse
import com.example.notes.Internationalization.Region.infra.webservice.CityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/city")
class CityServiceImplementation(private val cityUseCase: CityUseCase) : CityService {
    @PostMapping
    override fun createCity(@RequestBody city: City): CityResponse {
        return cityUseCase.createCity(city)
    }

    @GetMapping("/getByUUID/{cityUUID}")
    override fun getCityByUUID(@PathVariable("cityUUID") cityUUID: UUID): CityResponse {
        return cityUseCase.getCityByUUID(cityUUID)
    }

    @PostMapping("/update")
    override fun updateCity(@RequestBody city: City): CityResponse {
        return cityUseCase.updateCity(city)
    }

    @GetMapping
    override fun listAllCities(): CityArrayResponse {
        return cityUseCase.listAllCities()
    }
}