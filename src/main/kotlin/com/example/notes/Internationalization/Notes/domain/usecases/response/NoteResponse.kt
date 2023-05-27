package com.example.notes.Internationalization.Notes.domain.usecases.response

import com.example.notes.Internationalization.Notes.domain.entities.Note
import com.example.notes.Internationalization.Notes.domain.exception.NoteException

data class NoteResponse (val note : Note? = null, val error: NoteException? = null)
data class NoteArrayResponse (
    val noteList: List<Note>? = null,
    val page : Int? = null,
    val size : Int? = null,
    val numberPages : Int? = null,
    val error: NoteException? = null
)