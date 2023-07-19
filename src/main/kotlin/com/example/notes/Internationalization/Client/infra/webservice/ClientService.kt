package com.example.notes.Internationalization.Client.infra.webservice

import com.example.notes.Internationalization.Client.domain.entities.Client
import com.example.notes.Internationalization.Client.domain.usecases.response.ClientArrayResponse
import com.example.notes.Internationalization.Client.domain.usecases.response.ClientResponse
import java.util.*

interface ClientService {
    fun createClient(client: Client) : ClientResponse
    fun getClientByUUID(uuid: UUID) : ClientResponse
    fun updateClient(client: Client) : ClientResponse
    fun listClient() : ClientArrayResponse
}