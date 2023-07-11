package com.example.notes

import com.example.notes.Internationalization.Region.infra.repository.database.CityDatabase
import com.example.notes.Internationalization.Region.infra.repository.database.RegionDatabase
import com.example.notes.core.contact.infra.repository.database.ContactTypeDatabase
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NotesApplication

fun main(args: Array<String>) {
	Database.connect("jdbc:mysql://localhost:3306/newnotesdb", driver = "com.mysql.cj.jdbc.Driver", user = "root", password = "***")
	transaction {
		SchemaUtils.create(CityDatabase)
		SchemaUtils.create(RegionDatabase)
		SchemaUtils.create(ContactTypeDatabase)
	}
	runApplication<NotesApplication>(*args)
}
