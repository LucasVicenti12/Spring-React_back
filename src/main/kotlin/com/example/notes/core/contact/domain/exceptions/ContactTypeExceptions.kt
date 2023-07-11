package com.example.notes.core.contact.domain.exceptions

import com.example.notes.core.shared.error.GenericError

val CONTACTTYPE_GENERIC_ERROR = ContactTypeExceptions("CONTACTTYPE_GENERIC_ERROR", "An unexpected error has ocurred!")
val CONTACTTYPE_CODE_IS_ALREADY = ContactTypeExceptions("CONTACTTYPE_CODE_IS_ALREADY", "The code was already used")
val CONTACTTYPE_INVALID_CODE = ContactTypeExceptions("CONTACTTYPE_INVALID_CODE", "The code is invalid")
val CONTACTTYPE_INVALID_LABEL = ContactTypeExceptions("CONTACTTYPE_INVALID_LABEL", "The label is invalid")

class ContactTypeExceptions (code : String, description: String) : GenericError("contactType-module", code, description)