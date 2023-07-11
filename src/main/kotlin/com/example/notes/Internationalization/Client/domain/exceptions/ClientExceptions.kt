package com.example.notes.Internationalization.Client.domain.exceptions

import com.example.notes.core.shared.error.GenericError

val CLIENT_GENERIC_ERROR = ClientExceptions("CLIENT_GENERIC_ERROR", "An unexpected error has ocurred!")
val CLIENT_IDENTIFIER_ALREADY_EXISTS = ClientExceptions("CLIENT_IDENTIFIER_ALREADY_EXISTS", "The identifier was alredy exists")
val CLIENT_CODE_ALREADY_EXISTS = ClientExceptions("CLIENT_CODE_ALREADY_EXISTS", "The code was already exists")
val CLIENT_NAME_IS_INVALID = ClientExceptions("CLIENT_NAME_IS_INVALID", "The name is invalid")
val CLIENT_REGION_IS_INVALID = ClientExceptions("CLIENT_REGION_IS_INVALID", "The region is invalid")
val CLIENT_ONE_CONTACT_ONCE = ClientExceptions("CLIENT_ONE_CONTACT_ONCE", "You need to inform at least one contact")
val CLIENT_ADDRESS_IS_INVALID = ClientExceptions("CLIENT_ADDRESS_IS_INVALID", "The address is invalid")
val CLIENT_CODE_IS_INVALID = ClientExceptions("CLIENT_CODE_IS_INVALID", "The code is invalid")

class ClientExceptions(code: String, description: String) : GenericError("client-module", code, description)