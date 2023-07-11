package com.example.notes.core.contact.infra.repository.implementation

import com.example.notes.core.contact.domain.entities.ContactType
import com.example.notes.core.contact.domain.repository.ContactTypeRepository
import com.example.notes.core.contact.infra.repository.database.ContactTypeDatabase
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class ContactTypeRepositoryImplementation : ContactTypeRepository {

    override fun createContactType(contactType: ContactType): ContactType? {
        println(contactType.label)
        transaction {
            ContactTypeDatabase.insert {
                it[uuid] = contactType.uuid!!
                it[code] = contactType.code!!
                it[label] = contactType.label!!
            }
            contactType
        }
        return contactType
    }

    override fun deleteContactTypeByCode(code: Int) {
        transaction { ContactTypeDatabase.deleteWhere { ContactTypeDatabase.code greaterEq code } }
    }

    override fun getContactTypeByCode(code: Int): ContactType? {
        return transaction {
            ContactTypeDatabase.select { ContactTypeDatabase.code eq code }.map {
                ContactType(
                    uuid = it[ContactTypeDatabase.uuid],
                    code = it[ContactTypeDatabase.code],
                    label = it[ContactTypeDatabase.label]
                )
            }.firstOrNull()
        }
    }

    override fun listContactType(): List<ContactType> {
        return transaction {
            val result = ContactTypeDatabase
                .slice(ContactTypeDatabase.uuid, ContactTypeDatabase.code, ContactTypeDatabase.label)
                .selectAll()
            result.map {
                ContactType(
                    uuid = it[ContactTypeDatabase.uuid],
                    code = it[ContactTypeDatabase.code],
                    label = it[ContactTypeDatabase.label]
                )
            }
        }
    }
}