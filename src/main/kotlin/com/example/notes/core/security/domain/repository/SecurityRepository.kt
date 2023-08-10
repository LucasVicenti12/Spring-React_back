package com.example.notes.core.security.domain.repository

import com.example.notes.core.user.domain.entities.User

interface SecurityRepository {
    fun findUser(identifier : String) : User?
    fun craeteUser(user: User) : User?
}