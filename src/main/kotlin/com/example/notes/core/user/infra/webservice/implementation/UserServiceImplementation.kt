package com.example.notes.core.user.infra.webservice.implementation

import com.example.notes.core.user.domain.entity.User
import com.example.notes.core.user.domain.usecase.UserUsecase
import com.example.notes.core.user.domain.usecase.response.UserArrayResponse
import com.example.notes.core.user.domain.usecase.response.UserResponse
import com.example.notes.core.user.infra.webservice.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/user")
class UserServiceImplementation(
    private val userUsecase: UserUsecase
) : UserService {

    @PostMapping("/")
    override fun saveUser(user: User): UserResponse {
        return userUsecase.save(user)
    }

    @GetMapping("/getByUUID/{userUUID}")
    override fun getUserByUUID(@PathVariable("userUUID") userUUID: UUID): UserResponse {
        return userUsecase.getByUUID(userUUID)
    }

    @GetMapping("/")
    override fun listAll(): UserArrayResponse {
        return userUsecase.listAll()
    }
}