package com.example.notes.core.contact.domain.repository

import com.example.notes.core.contact.domain.entities.ContactType

interface ContactTypeRepository {
    fun createContactType(contactType: ContactType): ContactType?
    fun deleteContactTypeByCode(code : Int)
    fun getContactTypeByCode(code : Int) : ContactType?
    fun listContactType(): List<ContactType>
}