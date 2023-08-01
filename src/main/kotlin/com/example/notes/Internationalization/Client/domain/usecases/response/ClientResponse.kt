package com.example.notes.Internationalization.Client.domain.usecases.response

import com.example.notes.Internationalization.Client.domain.entities.Client
import com.example.notes.Internationalization.Client.domain.exceptions.ClientExceptions

data class ClientResponse(val client: Client? = null, val error: ClientExceptions? = null)
data class ClientArrayResponse(val clients: List<Client>? = null, val error: ClientExceptions? = null)