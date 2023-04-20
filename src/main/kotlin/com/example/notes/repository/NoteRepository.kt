package com.example.notes.repository

import com.example.notes.model.Note
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface NoteRepository : CrudRepository<Note, Long>