package com.example.notes.core.security.domain.entities

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class AuthEntryPoint : AuthenticationEntryPoint {

    companion object{
        private val logger = LoggerFactory.getLogger(AuthEntryPoint::class.java)
    }

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        logger.error("UnauthorizedHandler: ${authException.message}")

        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpServletResponse.SC_UNAUTHORIZED

        ObjectMapper().writeValue(
            response.outputStream, mapOf(
                "status" to HttpServletResponse.SC_UNAUTHORIZED,
                "error" to "Unauthorized",
                "message" to authException.message,
                "path" to request.servletPath
            )
        )
    }

}