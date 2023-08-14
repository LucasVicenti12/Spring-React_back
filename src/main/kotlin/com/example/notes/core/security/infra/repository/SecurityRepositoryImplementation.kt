package com.example.notes.core.security.infra.repository

import com.example.notes.core.security.domain.repository.SecurityRepository
import com.example.notes.core.user.domain.entities.User
import com.example.notes.core.user.infra.repository.database.UserDatabase
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SecurityRepositoryImplementation : SecurityRepository {
    override fun findUser(identifier: String): User? {
        return transaction {
            UserDatabase.select(Op.build { UserDatabase.authRecord eq identifier }).map {
                User(
                    uuid = it[UserDatabase.uuid],
                    name = it[UserDatabase.name],
                    email = it[UserDatabase.email],
                    authRecord = it[UserDatabase.authRecord],
                    password = null
                )
            }.firstOrNull()
        }
    }

    override fun craeteUser(user: User): User? {
        return transaction {
            UserDatabase.insert {
                it[uuid] = user.uuid!!
                it[name] = user.name!!
                it[authRecord] = user.authRecord!!
                it[email] = user.email!!
                it[password] = user.passwordHash!!
            }
            user
        }
    }
}