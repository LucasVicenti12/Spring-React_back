package com.example.notes.core.user.domain.exceptions

import com.example.notes.core.shared.error.GenericError

val USER_GENERIC_ERROR = UserException("USER_GENERIC_ERROR", "An unexpected error has ocurred!")
val USER_AUTH_ALREDY_EXITS = UserException("USER_AUTH_ALREDY_EXITS", "This auth record already exists!")
val USER_EMAIL_EXITS = UserException("USER_EMAIL_EXITS", "This email already used!")

class UserException (code : String, description: String) : GenericError("user-module", code, description)