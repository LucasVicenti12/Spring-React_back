package com.example.notes.core.contact.domain.usecases

import com.example.notes.core.contact.domain.entities.ContactType
import com.example.notes.core.contact.domain.usecases.response.ContactTypeArrayResponse
import com.example.notes.core.contact.domain.usecases.response.ContactTypeResponse

interface ContactTypeUseCase {
    fun createContactType(contactType: ContactType): ContactTypeResponse
    fun deleteContactTypeByCode(code : Int)
    fun listContactType(): ContactTypeArrayResponse
}