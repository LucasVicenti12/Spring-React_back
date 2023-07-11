package com.example.notes.Internationalization.Client.domain.repository

import com.example.notes.Internationalization.Client.domain.entities.Client
import java.util.UUID

interface ClientRepository {
    fun createClient(client: Client) : Client?
    fun getClientByUUID(uuid: UUID) : Client?
    fun updateClient(client: Client) : Client?
    fun listClient() : List<Client>
    fun getClientByCode(code: Int?) : Client?
    fun getClientByIdentifier(identifier : String) : Client?
}