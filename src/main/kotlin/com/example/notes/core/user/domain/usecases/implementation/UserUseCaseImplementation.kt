package com.example.notes.core.user.domain.usecases.implementation

import com.example.notes.core.user.domain.entities.User
import com.example.notes.core.user.domain.exceptions.USER_AUTH_ALREDY_EXITS
import com.example.notes.core.user.domain.exceptions.USER_EMAIL_EXITS
import com.example.notes.core.user.domain.exceptions.USER_GENERIC_ERROR
import com.example.notes.core.user.domain.repository.UserRepository
import com.example.notes.core.user.domain.usecases.UserUseCase
import com.example.notes.core.user.domain.usecases.response.UserArrayResponse
import com.example.notes.core.user.domain.usecases.response.UserResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class UserUseCaseImplementation (private val userRepository: UserRepository) : UserUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(UserUseCaseImplementation::class.java)
    }

    override fun createOrUpdateUser(user: User): UserResponse {
        return try {
            if(userRepository.getUserByAuthRecord(user.authRecord!!) != null){
                return UserResponse(user = null, error = USER_AUTH_ALREDY_EXITS)
            }
            if(userRepository.getUserByEmail(user.email!!) != null){
                return UserResponse(user = null, error = USER_EMAIL_EXITS)
            }
            UserResponse(user = userRepository.createOrUpdateUser(user), error = null)
        }catch (error : Exception){
            UserResponse(user = null, error = USER_GENERIC_ERROR)
        }
    }

    override fun getUserByAuthRecord(authRecord: String): UserResponse {
        return try {
            UserResponse(user = userRepository.getUserByAuthRecord(authRecord), error = null)
        }catch (error : Exception){
            UserResponse(user = null, error = USER_GENERIC_ERROR)
        }
    }

    override fun getUserByUUID(userUUID: UUID): UserResponse {
        return try {
            UserResponse(user = userRepository.getUserByUUID(userUUID), error = null)
        }catch (error : Exception){
            UserResponse(user = null, error = USER_GENERIC_ERROR)
        }
    }

    override fun getUserByEmail(userEmail: String): UserResponse {
        return try {
            UserResponse(user = userRepository.getUserByEmail(userEmail), error = null)
        }catch (error : Exception){
            UserResponse(user = null, error = USER_GENERIC_ERROR)
        }
    }

    override fun getAllUsers(): UserArrayResponse {
        return try {
            UserArrayResponse(users = userRepository.getAllUsers(), error = null)
        }catch (error : Exception){
            UserArrayResponse(users = null, error = USER_GENERIC_ERROR)
        }
    }
}