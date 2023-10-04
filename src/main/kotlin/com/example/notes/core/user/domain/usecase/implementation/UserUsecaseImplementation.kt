package com.example.notes.core.user.domain.usecase.implementation

import com.example.notes.core.user.domain.entity.User
import com.example.notes.core.user.domain.exceptions.*
import com.example.notes.core.user.domain.repository.UserRepository
import com.example.notes.core.user.domain.usecase.UserUsecase
import com.example.notes.core.user.domain.usecase.response.UserArrayResponse
import com.example.notes.core.user.domain.usecase.response.UserResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class UserUsecaseImplementation(
    private val userRepository: UserRepository
) : UserUsecase {

    companion object {
        private var logger = LoggerFactory.getLogger(UserUsecaseImplementation::class.java)
    }

    override fun save(user: User): UserResponse {
        return try {
            if (user.code != null && userRepository.getUserByCode(user.code!!) != null) {
                return UserResponse(error = USER_ALREADY_EXISTS)
            }
            if (user.username.isNullOrBlank()) {
                return UserResponse(error = USER_USERNAME_INVALID)
            }
            if (user.name.isNullOrBlank()) {
                return UserResponse(error = USER_NAME_INVALID)
            }
            if (user.email.isNullOrBlank()) {
                return UserResponse(error = USER_EMAIL_INVALID)
            }
            if (user.password.isNullOrBlank()) {
                return UserResponse(error = USER_PASSWORD_INVALID)
            }
            if (userRepository.getUserByUsername(user.username!!) != null) {
                return UserResponse(error = USER_USERNAME_ALREADY_EXISTS)
            }
            if (userRepository.getUserByEmail(user.email!!) != null) {
                return UserResponse(error = USER_EMAIL_ALREADY_EXISTS)
            }
            UserResponse(user = userRepository.save(user))
        } catch (e: Exception) {
            logger.error("USER", e)
            UserResponse(error = USER_GENERIC_ERROR)
        }
    }

    override fun getByUUID(userUUID: UUID): UserResponse {
        return try {
            val user = userRepository.getByUUID(userUUID) ?: return UserResponse(error = USER_USERNAME_DOESNT_EXISTS)
            UserResponse(user = user)
        } catch (e: Exception) {
            logger.error("USER", e)
            UserResponse(error = USER_GENERIC_ERROR)
        }
    }

    override fun getUserByCode(userCode: Int): UserResponse {
        return try {
            val user =
                userRepository.getUserByCode(userCode) ?: return UserResponse(error = USER_USERNAME_DOESNT_EXISTS)
            UserResponse(user = user)
        } catch (e: Exception) {
            logger.error("USER", e)
            UserResponse(error = USER_GENERIC_ERROR)
        }
    }

    override fun listAll(): UserArrayResponse {
        return try {
            UserArrayResponse(users = userRepository.listAll())
        } catch (e: Exception) {
            logger.error("USER", e)
            UserArrayResponse(error = USER_GENERIC_ERROR)
        }
    }
}