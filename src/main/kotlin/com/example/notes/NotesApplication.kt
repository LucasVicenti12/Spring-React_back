package com.example.notes

import com.example.notes.Internationalization.Client.infra.repository.database.ClientContactDataBase
import com.example.notes.Internationalization.Client.infra.repository.database.ClientDataBase
import com.example.notes.Internationalization.Region.infra.repository.database.CityDatabase
import com.example.notes.Internationalization.Region.infra.repository.database.RegionDatabase
import com.example.notes.core.contact.infra.repository.database.ContactTypeDatabase
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
	scanBasePackages = [
		"com.example.notes",
		"org.jetbrains.exposed.spring",
	],
)
class NotesApplication

fun main(args: Array<String>) {
	runApplication<NotesApplication>(*args)
}
