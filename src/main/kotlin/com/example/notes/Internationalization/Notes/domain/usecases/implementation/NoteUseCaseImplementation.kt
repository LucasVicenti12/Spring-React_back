package com.example.notes.Internationalization.Notes.domain.usecases.implementation

import com.example.notes.Internationalization.Notes.domain.entities.Note
import com.example.notes.Internationalization.Notes.domain.exception.NOTE_GENERIC_ERROR
import com.example.notes.Internationalization.Notes.domain.exception.NOTE_ISNT_EXISTS
import com.example.notes.Internationalization.Notes.domain.exception.NOTE_WITHOUT_DESCRIPTION
import com.example.notes.Internationalization.Notes.domain.exception.NOTE_WITHOUT_TITLE
import com.example.notes.Internationalization.Notes.domain.repository.NoteRepository
import com.example.notes.Internationalization.Notes.domain.usecases.NoteUseCase
import com.example.notes.Internationalization.Notes.domain.usecases.response.NoteArrayResponse
import com.example.notes.Internationalization.Notes.domain.usecases.response.NoteResponse
import com.example.notes.core.shared.webservice.BasicFilter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class NoteUseCaseImplementation (private val noteRepository: NoteRepository)  : NoteUseCase{

    companion object {
        private val logger = LoggerFactory.getLogger(NoteUseCaseImplementation::class.java)
    }

    override fun create(note: Note): NoteResponse {
        return try{
            if(note.title == "" || note.title == null){
                return NoteResponse(error = NOTE_WITHOUT_TITLE)
            }
            if(note.description == "" || note.description == null){
                return NoteResponse(error = NOTE_WITHOUT_DESCRIPTION)
            }
            note.uuid = UUID.randomUUID()
            noteRepository.create(note)
            NoteResponse()
        }catch (e : Exception){
            logger.error("NOTE", e)
            NoteResponse(error = NOTE_GENERIC_ERROR)
        }
    }

    override fun getByUUID(uuid: UUID): NoteResponse {
        try{
            noteRepository.getByUUID(uuid)
            return NoteResponse(note = noteRepository.getByUUID(uuid), error = null)
        }catch (e : Exception){
            return NoteResponse(error = NOTE_ISNT_EXISTS)
        }
    }

    override fun getByTitle(title: String): NoteResponse {
        try{
            noteRepository.getByTitle(title)
            return NoteResponse(note = noteRepository.getByTitle(title), error = null)
        }catch (e : Exception){
            return NoteResponse(error = NOTE_ISNT_EXISTS)
        }
    }

    override fun updateNote(note: Note): NoteResponse {
        return try{
            if(note.title == "" || note.title == null){
                return NoteResponse(error = NOTE_WITHOUT_TITLE)
            }
            if(note.description == "" || note.description == null){
                return NoteResponse(error = NOTE_WITHOUT_DESCRIPTION)
            }
            noteRepository.updateNote(note)
            NoteResponse()
        }catch (e : Exception){
            logger.error("NOTE", e)
            NoteResponse(error = NOTE_GENERIC_ERROR)
        }
    }

    override fun listAll(
        page: Int,
        size: Int,
        sortBy: String?,
        orderBy: String?,
        basicFilter: List<BasicFilter>?
    ): NoteArrayResponse {
        return try{
            NoteArrayResponse(
                noteList = noteRepository.listAll(page, size, sortBy, orderBy, basicFilter),
                page = page,
                size = size,
            )
        }catch (e : Exception){
            logger.error("NOTE", e)
            NoteArrayResponse(error = NOTE_GENERIC_ERROR)
        }
    }
}