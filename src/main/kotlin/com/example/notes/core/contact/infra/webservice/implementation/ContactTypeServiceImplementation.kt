package com.example.notes.core.contact.infra.webservice.implementation

import com.example.notes.core.contact.domain.entities.ContactType
import com.example.notes.core.contact.domain.usecases.ContactTypeUseCase
import com.example.notes.core.contact.domain.usecases.response.ContactTypeArrayResponse
import com.example.notes.core.contact.domain.usecases.response.ContactTypeResponse
import com.example.notes.core.contact.infra.webservice.ContactTypeService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/contactType")
class ContactTypeServiceImplementation(private val contactTypeUseCase: ContactTypeUseCase) : ContactTypeService {

    @PostMapping
    override fun createContactType(@RequestBody contactType: ContactType): ContactTypeResponse {
        return contactTypeUseCase.createContactType(contactType)
    }

    @DeleteMapping("/delete/{code}")
    override fun deleteContactTypeByCode(@PathVariable("code") code: Int) {
        return contactTypeUseCase.deleteContactTypeByCode(code)
    }

    @GetMapping
    override fun listContactType(): ContactTypeArrayResponse {
        return contactTypeUseCase.listContactType()
    }
}