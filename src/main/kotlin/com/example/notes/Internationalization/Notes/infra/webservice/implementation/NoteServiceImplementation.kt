package com.example.notes.Internationalization.Notes.infra.webservice.implementation

import com.example.notes.Internationalization.Notes.domain.entities.Note
import com.example.notes.Internationalization.Notes.domain.usecases.NoteUseCase
import com.example.notes.Internationalization.Notes.domain.usecases.response.NoteArrayResponse
import com.example.notes.Internationalization.Notes.domain.usecases.response.NoteResponse
import com.example.notes.Internationalization.Notes.infra.webservice.NoteService
import com.example.notes.core.shared.webservice.BasicFilter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/note")
class NoteServiceImplementation(
    private val noteUseCase: NoteUseCase
) : NoteService {
    @PostMapping
    override fun create(@RequestBody note: Note): NoteResponse {
        return noteUseCase.create(note)
    }

    @GetMapping("/getById/{noteUUID}")
    override fun getByUUID(@PathVariable("noteUUID") uuid: UUID): NoteResponse {
        return noteUseCase.getByUUID(uuid)
    }

    @GetMapping("/getByTitle/{noteTitle}")
    override fun getByTitle(@PathVariable("noteTitle") title: String): NoteResponse {
        return noteUseCase.getByTitle(title)
    }

    @PostMapping("/update")
    override fun updateNote(@RequestBody note: Note): NoteResponse {
        return noteUseCase.updateNote(note)
    }

    @GetMapping("/all")
    override fun listAll(
        @RequestParam("page", required = false, defaultValue="1") page: Int,
        @RequestParam("size", required = false, defaultValue="30") size: Int,
        @RequestParam("sortBy", required = false, defaultValue="asc") sortBy: String?,
        @RequestParam("orderBy", required = false, defaultValue="uuid") orderBy: String?,
        @RequestParam("filter", required = false) filter: List<BasicFilter>?
    ): NoteArrayResponse {
        println("CAIU AQUI VAGABUNDO")
        return noteUseCase.listAll(page, size, sortBy, orderBy, filter?.map { BasicFilter(it.name, it.value) })
    }
}