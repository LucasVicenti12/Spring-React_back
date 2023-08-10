package com.example.notes.core.user.infra.repository.implementation

import com.example.notes.core.user.domain.entities.User
import com.example.notes.core.user.domain.repository.UserRepository
import com.example.notes.core.user.infra.repository.database.UserDatabase
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserRepositoryImplementation : UserRepository {
    override fun createOrUpdateUser(user: User): User? {
        if (user.uuid === null) {
            user.uuid = UUID.randomUUID()
            transaction {
                UserDatabase.insert {
                    it[uuid] = user.uuid!!
                    it[name] = user.name!!
                    it[email] = user.email!!
                    it[authRecord] = user.authRecord!!
                    it[password] = user.password!!
                }
                user
            }
        } else {
            transaction {
                UserDatabase.update({ Op.build { UserDatabase.uuid eq user.uuid!! } }) {
                    it[name] = user.name!!
                    it[email] = user.email!!
                    it[authRecord] = user.authRecord!!
                }
            }
        }
        return user
    }

    override fun getUserByAuthRecord(authRecord: String): User? {
        return transaction {
            UserDatabase.select { Op.build { UserDatabase.authRecord eq authRecord } }.map {
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

    override fun getUserByUUID(userUUID: UUID): User? {
        return transaction {
            UserDatabase.select { Op.build { UserDatabase.uuid eq userUUID } }.map {
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

    override fun getUserByEmail(userEmail: String): User? {
        return transaction {
            UserDatabase.select { Op.build { UserDatabase.email eq userEmail } }.map {
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

    override fun getAllUsers(): List<User> {
        return transaction {
            UserDatabase.selectAll().map {
                User(
                    uuid = it[UserDatabase.uuid],
                    name = it[UserDatabase.name],
                    email = it[UserDatabase.email],
                    authRecord = it[UserDatabase.authRecord],
                    password = null
                )
            }
        }
    }
}