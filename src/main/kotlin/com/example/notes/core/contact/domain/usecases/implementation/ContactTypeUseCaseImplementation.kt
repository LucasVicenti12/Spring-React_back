package com.example.notes.core.contact.domain.usecases.implementation

import com.example.notes.core.contact.domain.entities.ContactType
import com.example.notes.core.contact.domain.exceptions.CONTACTTYPE_CODE_IS_ALREADY
import com.example.notes.core.contact.domain.exceptions.CONTACTTYPE_GENERIC_ERROR
import com.example.notes.core.contact.domain.exceptions.CONTACTTYPE_INVALID_CODE
import com.example.notes.core.contact.domain.exceptions.CONTACTTYPE_INVALID_LABEL
import com.example.notes.core.contact.domain.repository.ContactTypeRepository
import com.example.notes.core.contact.domain.usecases.ContactTypeUseCase
import com.example.notes.core.contact.domain.usecases.response.ContactTypeArrayResponse
import com.example.notes.core.contact.domain.usecases.response.ContactTypeResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContactTypeUseCaseImplementation(private val contactTypeRepository: ContactTypeRepository) : ContactTypeUseCase{
    companion object {
        private val logger = LoggerFactory.getLogger(ContactTypeUseCaseImplementation::class.java)
    }

    override fun createContactType(contactType: ContactType): ContactTypeResponse {
        return try {
            if(contactType.code == 0){
                return ContactTypeResponse(error = CONTACTTYPE_INVALID_CODE)
            }
            if(contactTypeRepository.getContactTypeByCode(contactType.code!!) != null){
                return ContactTypeResponse(error = CONTACTTYPE_CODE_IS_ALREADY)
            }
            if(contactType.label == ""){
                return ContactTypeResponse(error = CONTACTTYPE_INVALID_LABEL)
            }
            contactType.uuid = UUID.randomUUID()
            ContactTypeResponse(contact = contactTypeRepository.createContactType(contactType), error = null)
        }catch (e : Exception){
            logger.error("CONTACT_TYPE", e)
            ContactTypeResponse(error = CONTACTTYPE_GENERIC_ERROR)
        }
    }

    override fun deleteContactTypeByCode(code: Int) {
        return contactTypeRepository.deleteContactTypeByCode(code)
    }

    override fun listContactType(): ContactTypeArrayResponse {
        return try{
            return ContactTypeArrayResponse(contacts = contactTypeRepository.listContactType(), error = null)
        }catch (e : Exception){
            return ContactTypeArrayResponse(contacts = null, error = CONTACTTYPE_GENERIC_ERROR)
        }
    }
}