package com.example.notes.core.security.domain.usecase

import com.example.notes.core.user.domain.entities.User

interface SecurityUseCase {
    fun findUser(identifier : String) : User?
    fun craeteUser(user: User) : User?
}