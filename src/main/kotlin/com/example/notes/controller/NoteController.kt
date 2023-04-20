package com.example.notes.controller

import com.example.notes.model.Note
import com.example.notes.model.ResponseNote
import com.example.notes.services.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@CrossOrigin("*")
@RequestMapping("notes")
class NoteController {
    @Autowired
    lateinit var noteService: NoteService

    @GetMapping("/list")
    fun list() : List<Note>{
        return noteService.listAllNote()
    }

    @GetMapping("/note/{id}")
    fun getNoteById(@PathVariable id : Long) : Optional<Note> {
        return noteService.getNoteById(id)
    }

    @PostMapping("/add")
    fun add(@RequestBody note : Note) : ResponseEntity<ResponseNote> {
        return noteService.registerNote(note, "register")
    }

    @PostMapping("/alter")
    fun alter(@RequestBody note : Note) : ResponseEntity<ResponseNote> {
        return noteService.registerNote(note, "alter")
    }

    @DeleteMapping("/delete/{id}")
    fun deleteById(@PathVariable id : Long): ResponseEntity<ResponseNote> {
        return noteService.deleteNoteById(id)
    }
}