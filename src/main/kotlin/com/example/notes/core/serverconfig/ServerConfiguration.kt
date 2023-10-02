package com.example.notes.core.serverconfig

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class ServerConfiguration{
    @Bean
    fun corsConfiguration(): CorsConfigurationSource {
        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("*", CorsConfiguration().apply {
                allowedOrigins = listOf("*")
                allowedMethods = listOf("*")
                addAllowedHeader("*")
            })
        }
    }

    @Bean
    fun configure(http: HttpSecurity) : SecurityFilterChain{
        http.csrf().disable().cors().and().authorizeHttpRequests {
            it.requestMatchers("*").permitAll()
        }
        return http.build()
    }
}