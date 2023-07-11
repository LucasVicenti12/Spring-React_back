package com.example.notes.core.contact.domain.usecases.response

import com.example.notes.core.contact.domain.entities.ContactType
import com.example.notes.core.contact.domain.exceptions.ContactTypeExceptions

data class ContactTypeResponse (val contact: ContactType? = null, val error: ContactTypeExceptions? = null)
data class ContactTypeArrayResponse (val contacts: List<ContactType>? = null, val error: ContactTypeExceptions? = null)