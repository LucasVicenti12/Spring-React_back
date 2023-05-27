package com.example.notes.Internationalization.Notes.infra.repository.implementation

import com.example.notes.Internationalization.Notes.domain.entities.Note
import com.example.notes.Internationalization.Notes.domain.repository.NoteRepository
import com.example.notes.Internationalization.Notes.infra.repository.database.NoteDatabase
import com.example.notes.core.shared.webservice.BasicFilter
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Service
class NoteRepositoryImplementation : NoteRepository {
    override fun create(note: Note): Note? {
        transaction { NoteDatabase.insert {
            it[uuid] = note.uuid!!
            it[title] = note.title!!
            it[description] = note.description!!
        }}
        return note
    }

    override fun getByUUID(uuid: UUID): Note? {
        return transaction {
            NoteDatabase.select(
                {NoteDatabase.uuid eq uuid}
            )
                .map {
                    Note(
                        uuid = it[NoteDatabase.uuid],
                        title = it[NoteDatabase.title],
                        description = it[NoteDatabase.description],
                    )
                }.firstOrNull()
        }
    }

    override fun getByTitle(title: String): Note? {
        return transaction {
            NoteDatabase.select(
                {NoteDatabase.title eq title}
            )
                .map {
                    Note(
                        uuid = it[NoteDatabase.uuid],
                        title = it[NoteDatabase.title],
                        description = it[NoteDatabase.description],
                    )
                }.firstOrNull()
        }
    }

    override fun updateNote(note: Note) : Note {
        return transaction { NoteDatabase.update({
            NoteDatabase.uuid eq note.uuid!!
        }){
            it[title] = note.title!!
            it[description] = note.description!!
        }
            note
        }
    }

    override fun listAll(
        page: Int,
        size: Int,
        sortBy: String?,
        orderBy: String?,
        basicFilter: List<BasicFilter>?
    ): List<Note>? {
        return transaction {
            val result = NoteDatabase
                .slice(NoteDatabase.uuid, NoteDatabase.title, NoteDatabase.description)
                .selectAll()
                .limit(size, offset = (((page - 1) * size).toLong()))
                .orderBy(
                    when(sortBy) {
                    "desc" -> when(orderBy){
                        "title" -> NoteDatabase.title to SortOrder.DESC
                        else -> NoteDatabase.uuid to SortOrder.DESC
                    }
                    "asc" -> when(orderBy){
                        "title" -> NoteDatabase.title to SortOrder.ASC
                        else -> NoteDatabase.uuid to SortOrder.ASC
                    }
                    else -> NoteDatabase.uuid to SortOrder.ASC
                })
            result.map {
                Note(
                    uuid = it[NoteDatabase.uuid],
                    title = it[NoteDatabase.title],
                    description = it[NoteDatabase.description]
                ) }
        }
    }

}