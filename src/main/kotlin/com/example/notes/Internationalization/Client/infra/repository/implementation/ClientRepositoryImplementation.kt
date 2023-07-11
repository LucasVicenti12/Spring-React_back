package com.example.notes.Internationalization.Client.infra.repository.implementation

import com.example.notes.Internationalization.Client.domain.entities.Client
import com.example.notes.Internationalization.Client.domain.entities.Contact
import com.example.notes.Internationalization.Client.domain.repository.ClientRepository
import com.example.notes.Internationalization.Client.infra.repository.database.ClientContactDataBase
import com.example.notes.Internationalization.Client.infra.repository.database.ClientDataBase
import com.example.notes.core.contact.domain.entities.ContactType
import com.example.notes.core.contact.infra.repository.database.ContactTypeDatabase
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ClientRepositoryImplementation : ClientRepository {
    override fun createClient(client: Client): Client? {
        transaction {
            ClientDataBase.insert {
                it[uuid] = client.uuid!!
                it[name] = client.name!!
                it[code] = client.code!!
                it[identifier] = client.identifier!!
                it[regionCode] = client.regionCode!!
                it[situation] = client.situation!!
                it[description] = client.description!!
                it[address] = client.address
            }
            client
        }
        client.contacts?.map {
            val contact: Contact? = it
            contact?.uuid = UUID.randomUUID()
            transaction {
                ClientContactDataBase.insert {
                    it[uuid] = contact!!.uuid!!
                    it[contactTypeCode] = contact!!.contactTypeCode!!
                    it[description] = contact!!.description!!
                    it[createdAt] = LocalDateTime.now()
                    it[clientCode] = client.code!!
                }
            }
        }
        return client
    }

    override fun getClientByUUID(uuid: UUID): Client? {
        TODO("Not yet implemented")
    }

    override fun updateClient(client: Client): Client? {
        TODO("Not yet implemented")
    }

    override fun listClient(): List<Client> {
        return transaction {
            ClientDataBase
                .selectAll().map {
                    Client(
                        uuid = it[ClientDataBase.uuid],
                        name = it[ClientDataBase.name],
                        code = it[ClientDataBase.code],
                        identifier = it[ClientDataBase.identifier],
                        regionCode = it[ClientDataBase.regionCode],
                        situation = it[ClientDataBase.situation],
                        description = it[ClientDataBase.description],
                        address = it[ClientDataBase.address],
                        contacts = getContactsByClientCode(it[ClientDataBase.code])
                    )
                }
        }
    }

    override fun getClientByCode(code: Int?): Client? {
        TODO("Not yet implemented")
    }

    override fun getClientByIdentifier(identifier: String): Client? {
        TODO("Not yet implemented")
    }

    fun getContactsByClientCode(code: Int): List<Contact> {
        return transaction {
            ClientContactDataBase
                .select(
                    Op.build { ClientContactDataBase.clientCode eq code }
                ).map {
                    Contact(
                        uuid = it[ClientContactDataBase.uuid],
                        contactTypeCode = it[ClientContactDataBase.contactTypeCode],
                        description = it[ClientContactDataBase.description],
                        createdAt = it[ClientContactDataBase.createdAt],
                        clientCode = it[ClientContactDataBase.clientCode],
                        contactType = getContactTypeByTypeCode(it[ClientContactDataBase.contactTypeCode])
                    )
                }
        }
    }

    fun getContactTypeByTypeCode(code: Int): ContactType {
        return transaction {
            ContactTypeDatabase
                .select(
                    Op.build { ContactTypeDatabase.code eq code }
                ).map {
                    ContactType(
                        uuid = it[ContactTypeDatabase.uuid],
                        label = it[ContactTypeDatabase.label],
                        code = it[ContactTypeDatabase.code]
                    )
                }.firstOrNull()!!
        }
    }
}