package com.example.notes.core.security.domain.config

import com.example.notes.core.security.domain.entities.AuthEntryPoint
import com.example.notes.core.security.domain.entities.AuthTokenFilter
import com.example.notes.core.security.domain.entities.CustomAccessDenied
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
class SecurityConfig {

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var unauthorizedHandler: AuthEntryPoint

    @Autowired
    private lateinit var accessDeniedHandler: CustomAccessDenied

    @Bean
    fun authenticationJwtTokenFilter(): AuthTokenFilter = AuthTokenFilter()

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        return DaoAuthenticationProvider().also {
            it.setPasswordEncoder(passwordEncoder)
            it.setUserDetailsService(userDetailsService)
        }
    }

    @Bean
    fun corsConfiguration(): CorsConfigurationSource {
        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", CorsConfiguration().apply {
                allowedOrigins = listOf("*")
                allowedMethods = listOf("*")
                allowCredentials = true
                addAllowedHeader("*")
            })
        }
    }

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable().cors().and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeHttpRequests {
                it
                    .requestMatchers("*").permitAll()
            }
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

}