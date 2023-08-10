package com.example.notes.core.security.domain.exceptions

import com.example.notes.core.shared.error.GenericError

val ACCESS_DENIED = SecurityException("ACCESS_DENIED", "User don't have access to this action!")

class SecurityException (code: String, description: String) : GenericError("security-module", code, description)