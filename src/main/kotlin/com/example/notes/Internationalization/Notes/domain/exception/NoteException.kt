package com.example.notes.Internationalization.Notes.domain.exception

import com.example.notes.core.shared.error.GenericError

val NOTE_GENERIC_ERROR = NoteException("NOTE_GENERIC_ERROR", "An error has ocurred")
val NOTE_WITHOUT_TITLE = NoteException("NOTE_WITHOUT_TITLE", "You need to inform an title")
val NOTE_WITHOUT_DESCRIPTION = NoteException("NOTE_WITHOUT_DESCRIPTION", "You need to inform an description")
val NOTE_ISNT_EXISTS = NoteException("NOTE_ISNT_EXISTS", "The informed note isn't exists")

class NoteException (code : String, description: String) : GenericError("note-module", code, description)