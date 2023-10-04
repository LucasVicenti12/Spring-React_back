package com.example.notes.core.user.domain.usecase.response

import com.example.notes.core.user.domain.entity.User
import com.example.notes.core.user.domain.exceptions.UserException

data class UserResponse(val user: User? = null, val error: UserException? = null)
data class UserArrayResponse(val users: List<User>? = listOf(), val error: UserException? = null)