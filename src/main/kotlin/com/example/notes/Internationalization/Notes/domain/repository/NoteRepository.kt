package com.example.notes.Internationalization.Notes.domain.repository

import com.example.notes.Internationalization.Notes.domain.entities.Note
import com.example.notes.core.shared.webservice.BasicFilter
import org.springframework.stereotype.Repository
import java.util.UUID

interface NoteRepository {
    fun create (note : Note) : Note?
    fun getByUUID (uuid: UUID) : Note?
    fun getByTitle (title : String) : Note?
    fun updateNote (note : Note) : Note?
    fun listAll(
        page: Int,
        size: Int,
        sortBy: String?,
        orderBy: String?,
        basicFilter: List<BasicFilter>?
    ) : List<Note>?
}