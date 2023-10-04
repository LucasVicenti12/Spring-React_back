package com.example.notes.core.user.domain.exceptions

import com.example.notes.core.shared.error.GenericError

val USER_GENERIC_ERROR = UserException("USER_GENERIC_ERROR", "An unexpected error has ocurred!")
val USER_ALREADY_EXISTS = UserException("USER_ALREADY_EXISTS", "User already exists!")
val USER_USERNAME_INVALID = UserException("USER_USERNAME_INVALID", "This username is invalid")
val USER_NAME_INVALID = UserException("USER_NAME_INVALID", "This name is invalid")
val USER_EMAIL_INVALID = UserException("USER_EMAIL_INVALID", "This email is invalid")
val USER_PASSWORD_INVALID = UserException("USER_PASSWORD_INVALID", "This email is invalid")
val USER_USERNAME_ALREADY_EXISTS = UserException("USER_USERNAME_ALREADY_EXISTS", "This username already used!")
val USER_USERNAME_DOESNT_EXISTS = UserException("USER_USERNAME_DOESNT_EXISTS", "This username doesn't exists!")
val USER_EMAIL_ALREADY_EXISTS = UserException("USER_EMAIL_ALREADY_EXISTS", "This email already used")

class UserException (code: String, description: String) : GenericError("USER_MODULE", code, description)