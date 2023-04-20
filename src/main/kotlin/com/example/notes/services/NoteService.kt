package com.example.notes.services

import com.example.notes.controller.NoteController
import com.example.notes.model.Note
import com.example.notes.model.ResponseNote
import com.example.notes.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class NoteService {

    @Autowired
    lateinit var noteRepository: NoteRepository

    @Autowired
    lateinit var responseNote: ResponseNote

    fun listAllNote() : List<Note>{
        return noteRepository.findAll().toList()
    }

    fun getNoteById(id : Long) : Optional<Note> {
        return noteRepository.findById(id)
    }

    fun queryNote(id: Long) : Boolean{
        return noteRepository.existsById(id)
    }

    fun registerNote(note : Note, action : String) : ResponseEntity<ResponseNote> {
        if(note.title.equals("")){
            responseNote.response = "You need to inform the title"
            return ResponseEntity<ResponseNote>(responseNote, HttpStatus.BAD_REQUEST)
        }
        if(note.description.equals("")){
            responseNote.response = "You need to inform the description"
            return ResponseEntity<ResponseNote>(responseNote, HttpStatus.BAD_REQUEST)
        }
        noteRepository.save(note)
        responseNote.response = "Saved"
        return ResponseEntity<ResponseNote>(responseNote, HttpStatus.CREATED);
    }

    fun deleteNoteById(id : Long) : ResponseEntity<ResponseNote>{
        if(id.equals(0)){
            responseNote.response = "The note is invalid"
            return ResponseEntity<ResponseNote>(responseNote, HttpStatus.OK)
        }
        noteRepository.deleteById(id)
        responseNote.response = "The note is deleted"
        return ResponseEntity<ResponseNote>(responseNote, HttpStatus.OK)
    }
}