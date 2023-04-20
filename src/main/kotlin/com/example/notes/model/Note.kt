package com.example.notes.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.*

@Entity
class Note(@Id @GeneratedValue val id: Long, val title: String = "", val description: String = "")