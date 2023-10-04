package com.example.notes.core.user.domain.usecase

import com.example.notes.core.user.domain.entity.User
import com.example.notes.core.user.domain.usecase.response.UserArrayResponse
import com.example.notes.core.user.domain.usecase.response.UserResponse
import java.util.UUID

interface UserUsecase {
    fun save(user: User): UserResponse
    fun getByUUID(userUUID: UUID): UserResponse
    fun getUserByCode(userCode: Int): UserResponse
    fun listAll(): UserArrayResponse
}