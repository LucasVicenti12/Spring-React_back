package com.example.notes.core.user.domain.repository

import com.example.notes.core.user.domain.entity.User
import java.util.*

interface UserRepository {
    fun save(user: User): User?
    fun getByUUID(userUUID: UUID): User?
    fun getUserByCode(userCode: Int): User?
    fun getUserByUsername(username: String): User?
    fun getUserByEmail(userEmail: String): User?
    fun listAll(): List<User>?
}