package com.example.notes.core.user.infra.webservice

import com.example.notes.core.user.domain.entities.User
import com.example.notes.core.user.domain.usecases.response.UserArrayResponse
import com.example.notes.core.user.domain.usecases.response.UserResponse
import java.util.*

interface UserService {
    fun createOrUpdateUser(user: User): UserResponse
    fun getUserByAuthRecord(authRecord: String): UserResponse
    fun getUserByUUID(userUUID: UUID): UserResponse
    fun getUserByEmail(userEmail: String): UserResponse
    fun getAllUsers(): UserArrayResponse
}