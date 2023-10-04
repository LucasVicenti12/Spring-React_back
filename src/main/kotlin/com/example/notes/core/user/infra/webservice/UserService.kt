package com.example.notes.core.user.infra.webservice

import com.example.notes.core.user.domain.entity.User
import com.example.notes.core.user.domain.usecase.response.UserArrayResponse
import com.example.notes.core.user.domain.usecase.response.UserResponse
import java.util.UUID

interface UserService {
    fun saveUser(user: User) : UserResponse
    fun getUserByUUID(userUUID: UUID) : UserResponse
    fun listAll() : UserArrayResponse
}