package com.example.notes.core.contact.infra.webservice

import com.example.notes.core.contact.domain.entities.ContactType
import com.example.notes.core.contact.domain.usecases.response.ContactTypeArrayResponse
import com.example.notes.core.contact.domain.usecases.response.ContactTypeResponse

interface ContactTypeService {
    fun createContactType(contactType: ContactType): ContactTypeResponse
    fun deleteContactTypeByCode(code : Int)
    fun listContactType(): ContactTypeArrayResponse
}