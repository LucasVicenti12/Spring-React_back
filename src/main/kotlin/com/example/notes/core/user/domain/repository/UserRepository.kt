package com.example.notes.core.user.domain.repository

import com.example.notes.core.user.domain.entities.User
import java.util.UUID

interface UserRepository {
    fun createOrUpdateUser(user: User): User?
    fun getUserByAuthRecord(authRecord: String): User?
    fun getUserByUUID(userUUID: UUID): User?
    fun getUserByEmail(userEmail: String): User?
    fun getAllUsers(): List<User>
}