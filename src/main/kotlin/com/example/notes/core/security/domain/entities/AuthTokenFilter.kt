package com.example.notes.core.security.domain.entities

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter

class AuthTokenFilter : OncePerRequestFilter() {
    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Autowired
    private lateinit var tokenHelper: TokenHelper
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val token = tokenHelper.getJwtFromCookies(request)
            if (token != null && tokenHelper.validateJwtToken(token)) {
                val userUUID = tokenHelper.getUserUUIDFromJwtToken(token)
                val userDetails = userDetailsService.loadUserByUsername(userUUID)
                val authentication = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            logger.error("Cannot set user authentication: {}", e)
        }

        filterChain.doFilter(request, response)
    }


}