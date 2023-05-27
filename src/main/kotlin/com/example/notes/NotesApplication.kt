package com.example.notes

import com.example.notes.Internationalization.Notes.infra.repository.database.NoteDatabase
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
		SchemaUtils.create(NoteDatabase)
	}
	runApplication<NotesApplication>(*args)
}
