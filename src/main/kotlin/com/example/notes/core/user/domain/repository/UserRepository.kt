package com.example.notes.core.user.domain.repository

import com.example.notes.core.user.domain.entities.User

interface UserRepository {
    fun createOrUpdateUser(user: User): User?
    fun getUserByAuthRecord(authRecord: String): User?
    fun getUserByUUID(userUUID: String): User?
    fun getUserByEmail(userEmail: String): User?
    fun getAllUsers(): List<User>
}