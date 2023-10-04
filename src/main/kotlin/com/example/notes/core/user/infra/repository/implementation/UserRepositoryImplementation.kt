package com.example.notes.core.user.infra.repository.implementation

import com.example.notes.core.user.domain.entity.User
import com.example.notes.core.user.domain.repository.UserRepository
import com.example.notes.core.user.infra.repository.database.UserDatabase
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserRepositoryImplementation : UserRepository {
    override fun save(user: User): User? {
        if(user.uuid == null){
            user.uuid = UUID.randomUUID()
        }
        return transaction {
            UserDatabase.insert {
                it[uuid] = user.uuid!!
                it[code] = user.code!!
                it[name] = user.name!!
                it[username] = user.username!!
                it[email] = user.email!!
                it[password] = user.password!!
            }
            user
        }
    }

    override fun getByUUID(userUUID: UUID): User? {
        return transaction {
            UserDatabase.select(Op.build { UserDatabase.uuid eq userUUID }).map {
                User(
                    uuid = it[UserDatabase.uuid],
                    code = it[UserDatabase.code],
                    name = it[UserDatabase.name],
                    username = it[UserDatabase.username],
                    email = it[UserDatabase.email],
                    password = null,
                )
            }.firstOrNull()
        }
    }

    override fun getUserByCode(userCode: Int): User? {
        return transaction {
            UserDatabase.select(Op.build { UserDatabase.code eq userCode }).map {
                User(
                    uuid = it[UserDatabase.uuid],
                    code = it[UserDatabase.code],
                    name = it[UserDatabase.name],
                    username = it[UserDatabase.username],
                    email = it[UserDatabase.email],
                    password = null,
                )
            }.firstOrNull()
        }
    }

    override fun getUserByUsername(username: String): User? {
        return transaction {
            UserDatabase.select(Op.build { UserDatabase.username eq username }).map {
                User(
                    uuid = it[UserDatabase.uuid],
                    code = it[UserDatabase.code],
                    name = it[UserDatabase.name],
                    username = it[UserDatabase.username],
                    email = it[UserDatabase.email],
                    password = null,
                )
            }.firstOrNull()
        }
    }

    override fun getUserByEmail(userEmail: String): User? {
        return transaction {
            UserDatabase.select(Op.build { UserDatabase.email eq userEmail }).map {
                User(
                    uuid = it[UserDatabase.uuid],
                    code = it[UserDatabase.code],
                    name = it[UserDatabase.name],
                    username = it[UserDatabase.username],
                    email = it[UserDatabase.email],
                    password = null,
                )
            }.firstOrNull()
        }
    }

    override fun listAll(): List<User>? {
        return transaction {
            UserDatabase.selectAll().map {
                User(
                    uuid = it[UserDatabase.uuid],
                    code = it[UserDatabase.code],
                    name = it[UserDatabase.name],
                    username = it[UserDatabase.username],
                    email = it[UserDatabase.email],
                    password = null,
                )
            }.toList()
        }
    }
}