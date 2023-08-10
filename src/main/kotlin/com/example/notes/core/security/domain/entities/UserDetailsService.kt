package com.example.notes.core.security.domain.entities

import com.example.notes.core.security.domain.usecase.SecurityUseCase
import com.example.notes.core.user.domain.entities.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsService (private val securityUseCase: SecurityUseCase) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {
        return securityUseCase.findUser(username)?.run {
            SystemUser(this, listOf())
        }
    }

}