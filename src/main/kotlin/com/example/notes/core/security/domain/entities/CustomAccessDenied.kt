package com.example.notes.core.security.domain.entities

import com.example.notes.core.security.domain.exceptions.ACCESS_DENIED
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class CustomAccessDenied : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        response.status = HttpStatus.FORBIDDEN.value()
        val errorResponse = ACCESS_DENIED
        val objectMapper = ObjectMapper()
        val errorJson = objectMapper.writeValueAsString(errorResponse)
        response.writer.write(errorJson)
    }
}