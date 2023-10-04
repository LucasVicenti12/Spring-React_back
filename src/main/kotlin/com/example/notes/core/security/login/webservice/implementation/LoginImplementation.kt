package com.example.notes.core.security.login.webservice.implementation

import com.example.notes.core.security.login.entity.Login
import com.example.notes.core.security.login.webservice.LoginEndpoint
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class LoginImplementation : LoginEndpoint {

    @PostMapping("/login")
    override fun login(login: Login) {
        TODO("Not yet implemented")
    }
}