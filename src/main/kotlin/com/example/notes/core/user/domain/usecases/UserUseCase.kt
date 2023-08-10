package com.example.notes.core.user.domain.usecases

import com.example.notes.core.user.domain.entities.User
import com.example.notes.core.user.domain.usecases.response.UserArrayResponse
import com.example.notes.core.user.domain.usecases.response.UserResponse
import java.util.UUID

interface UserUseCase {
    fun createOrUpdateUser(user: User): UserResponse
    fun getUserByAuthRecord(authRecord: String): UserResponse
    fun getUserByUUID(userUUID: UUID): UserResponse
    fun getUserByEmail(userEmail: String): UserResponse
    fun getAllUsers(): UserArrayResponse
}