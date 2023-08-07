package com.example.notes.core.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.WebSecurityConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        return DaoAuthenticationProvider().also {
            it.setPasswordEncoder(passwordEncoder)
            it.setUserDetailsService(userDetailsService)
        }
    }

    @Bean
    fun configure(http: HttpSecurity) : SecurityFilterChain{
        http.csrf().disable().cors().and()
            .exceptionHandling()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeHttpRequests{
                it
                    .requestMatchers("*").permitAll()
            }
            .authenticationProvider(authenti)
//            .accessDeniedHandler()
//            .authenticationEntryPoint()
            .
    }

}