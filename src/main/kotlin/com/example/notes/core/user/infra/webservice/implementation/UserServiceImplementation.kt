package com.example.notes.core.user.infra.webservice.implementation

import com.example.notes.core.user.domain.entities.User
import com.example.notes.core.user.domain.usecases.UserUseCase
import com.example.notes.core.user.domain.usecases.response.UserArrayResponse
import com.example.notes.core.user.domain.usecases.response.UserResponse
import com.example.notes.core.user.infra.webservice.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/product")
class UserServiceImplementation (private val userUseCase: UserUseCase) : UserService {

    @PostMapping
    override fun createOrUpdateUser(@RequestBody user: User): UserResponse {
        return userUseCase.createOrUpdateUser(user)
    }

    @GetMapping("/getByAuthRecord/{authRecord}")
    override fun getUserByAuthRecord(@PathVariable("authRecord") authRecord: String): UserResponse {
        return userUseCase.getUserByAuthRecord(authRecord)
    }

    @GetMapping("/getByUserUUID/{userUUID}")
    override fun getUserByUUID(@PathVariable("userUUID") userUUID: UUID): UserResponse {
        return userUseCase.getUserByUUID(userUUID)
    }

    @GetMapping("/getByEmail/{userEmail}")
    override fun getUserByEmail(@PathVariable("userEmail") userEmail: String): UserResponse {
        return userUseCase.getUserByEmail(userEmail)
    }

    @GetMapping
    override fun getAllUsers(): UserArrayResponse {
        return userUseCase.getAllUsers()
    }
}