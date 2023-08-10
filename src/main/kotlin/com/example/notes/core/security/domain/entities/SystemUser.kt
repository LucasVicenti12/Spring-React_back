package com.example.notes.core.security.domain.entities

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import java.util.*
import com.example.notes.core.user.domain.entities.User as UserUI

class SystemUser (private val user : UserUI, private val roles : List<GrantedAuthority>) : User(
    user.authRecord,
    user.passwordHash,
    true,
    true,
    true,
    true,
    roles
) {
    fun getUserData(): UserUI = user.copy(password = null)
    val uuid: UUID
        get() = user.uuid!!
}