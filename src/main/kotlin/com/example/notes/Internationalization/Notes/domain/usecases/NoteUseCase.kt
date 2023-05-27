package com.example.notes.Internationalization.Notes.domain.usecases

import com.example.notes.Internationalization.Notes.domain.entities.Note
import com.example.notes.Internationalization.Notes.domain.usecases.response.NoteArrayResponse
import com.example.notes.Internationalization.Notes.domain.usecases.response.NoteResponse
import com.example.notes.core.shared.webservice.BasicFilter
import java.util.*

interface NoteUseCase {
    fun create (note : Note) : NoteResponse
    fun getByUUID (uuid: UUID) : NoteResponse
    fun getByTitle (title : String) : NoteResponse
    fun updateNote (note : Note) : NoteResponse
    fun listAll(
        page: Int,
        size: Int,
        sortBy: String?,
        orderBy: String?,
        basicFilter: List<BasicFilter>?
    ) : NoteArrayResponse
}