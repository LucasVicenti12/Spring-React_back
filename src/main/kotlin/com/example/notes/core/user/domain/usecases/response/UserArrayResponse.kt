package com.example.notes.core.user.domain.usecases.response

import com.example.notes.core.user.domain.entities.User
import com.example.notes.core.user.domain.exceptions.UserException

class UserArrayResponse (users: List<User>? = listOf(), error : UserException?)