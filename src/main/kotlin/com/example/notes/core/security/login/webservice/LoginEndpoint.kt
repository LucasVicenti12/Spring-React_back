package com.example.notes.core.security.login.webservice

import com.example.notes.core.security.login.entity.Login

interface LoginEndpoint {
    fun login(login: Login)
}