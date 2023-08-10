package com.example.notes.core.security.domain.usecase.implementation

import com.example.notes.core.security.domain.usecase.SecurityUseCase
import com.example.notes.core.user.domain.entities.User
import org.springframework.stereotype.Service

@Service
class SecurityUseCaseImplementation : SecurityUseCase {
    override fun findUser(identifier: String): User? {
        TODO("Not yet implemented")
    }

    override fun craeteUser(user: User): User? {
        TODO("Not yet implemented")
    }
}