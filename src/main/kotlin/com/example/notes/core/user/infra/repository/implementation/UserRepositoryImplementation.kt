package com.example.notes.core.user.infra.repository.implementation

import com.example.notes.core.user.domain.entities.User
import com.example.notes.core.user.domain.repository.UserRepository
import com.example.notes.core.user.infra.repository.database.UserDatabase
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.*

class UserRepositoryImplementation : UserRepository {
    override fun createOrUpdateUser(user: User): User? {
        if(user.uuid === null){
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
        }else{
            transaction {
                UserDatabase.update({ Op.build { UserDatabase.uuid eq user.uuid!! } }){
                    it[name] = user.name!!
                    it[email] = user.email!!
                    it[authRecord] = user.authRecord!!
                }
            }
        }
        return user
    }

    override fun getUserByAuthRecord(authRecord: String): User? {
        TODO("Not yet implemented")
    }

    override fun getUserByUUID(userUUID: String): User? {
        TODO("Not yet implemented")
    }

    override fun getUserByEmail(userEmail: String): User? {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(): List<User> {
        TODO("Not yet implemented")
    }
}