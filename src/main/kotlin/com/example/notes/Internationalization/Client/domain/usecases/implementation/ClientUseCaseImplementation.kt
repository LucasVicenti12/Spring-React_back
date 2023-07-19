package com.example.notes.Internationalization.Client.domain.usecases.implementation

import com.example.notes.Internationalization.Client.domain.entities.Client
import com.example.notes.Internationalization.Client.domain.exceptions.*
import com.example.notes.Internationalization.Client.domain.repository.ClientRepository
import com.example.notes.Internationalization.Client.domain.usecases.ClientUseCase
import com.example.notes.Internationalization.Client.domain.usecases.response.ClientArrayResponse
import com.example.notes.Internationalization.Client.domain.usecases.response.ClientResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClientUseCaseImplementation(private val clientRepository: ClientRepository) : ClientUseCase {
    companion object {
        private val logger = LoggerFactory.getLogger(ClientUseCaseImplementation::class.java)
    }

    override fun createClient(client: Client): ClientResponse {
        return try {
            if (client.code == 0) {
                return ClientResponse(error = CLIENT_CODE_IS_INVALID)
            }
            if (clientRepository.getClientByCode(client.code!!) != null) {
                return ClientResponse(error = CLIENT_CODE_ALREADY_EXISTS)
            }
            if (client.contacts!!.isEmpty()) {
                return ClientResponse(error = CLIENT_ONE_CONTACT_ONCE)
            }
            if (client.name == "") {
                return ClientResponse(error = CLIENT_NAME_IS_INVALID)
            }
            if (client.address == "") {
                return ClientResponse(error = CLIENT_ADDRESS_IS_INVALID)
            }
            if (client.regionCode == 0) {
                return ClientResponse(error = CLIENT_REGION_IS_INVALID)
            }
            if (clientRepository.getClientByIdentifier(client.identifier!!) != null) {
                return ClientResponse(error = CLIENT_IDENTIFIER_ALREADY_EXISTS)
            }
            client.uuid = UUID.randomUUID()
            ClientResponse(client = clientRepository.createClient(client), error = null)
        } catch (e: Exception) {
            logger.error("CLIENT", e)
            ClientResponse(error = CLIENT_GENERIC_ERROR)
        }
    }

    override fun getClientByUUID(uuid: UUID): ClientResponse {
        return try {
            ClientResponse(client = clientRepository.getClientByUUID(uuid), error = null)
        } catch (e: Exception) {
            logger.error("CLIENT", e)
            ClientResponse(error = CLIENT_GENERIC_ERROR)
        }
    }

    override fun updateClient(client: Client): ClientResponse {
        return try {
            if (client.name == "") {
                return ClientResponse(error = CLIENT_NAME_IS_INVALID)
            }
            if (client.address == "") {
                return ClientResponse(error = CLIENT_ADDRESS_IS_INVALID)
            }
            if (client.regionCode == 0) {
                return ClientResponse(error = CLIENT_REGION_IS_INVALID)
            }
            ClientResponse(client = clientRepository.updateClient(client), error = null)
        } catch (e: Exception) {
            logger.error("CLIENT", e)
            return ClientResponse(error = CLIENT_GENERIC_ERROR)
        }
    }

    override fun listClient(): ClientArrayResponse {
        return try {
            ClientArrayResponse(clients = clientRepository.listClient(), error = null)
        } catch (e: Exception) {
            logger.error("CLIENT", e)
            return ClientArrayResponse(error = CLIENT_GENERIC_ERROR)
        }
    }
}