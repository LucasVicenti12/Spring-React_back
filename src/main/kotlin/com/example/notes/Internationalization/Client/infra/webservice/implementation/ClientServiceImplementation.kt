package com.example.notes.Internationalization.Client.infra.webservice.implementation

import com.example.notes.Internationalization.Client.domain.entities.Client
import com.example.notes.Internationalization.Client.domain.usecases.ClientUseCase
import com.example.notes.Internationalization.Client.domain.usecases.response.ClientArrayResponse
import com.example.notes.Internationalization.Client.domain.usecases.response.ClientResponse
import com.example.notes.Internationalization.Client.infra.webservice.ClientService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/client")
class ClientServiceImplementation(private val clientUseCase: ClientUseCase) : ClientService {

    @PostMapping
    override fun createOrUpdateClient(@RequestBody client: Client): ClientResponse {
        return clientUseCase.createOrUpdateClient(client)
    }

    @GetMapping("/getByUUID/{uuid}")
    override fun getClientByUUID(@PathVariable("uuid") uuid: UUID): ClientResponse {
        return clientUseCase.getClientByUUID(uuid)
    }

    @GetMapping
    override fun listClient(): ClientArrayResponse {
        return clientUseCase.listClient()
    }
}