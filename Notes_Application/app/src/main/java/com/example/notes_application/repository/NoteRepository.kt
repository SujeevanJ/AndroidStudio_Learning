package com.example.notes_application.repository

import com.example.notes_application.data.local.Note
import com.example.notes_application.data.local.NoteDao

class NoteRepository(
    private val dao: NoteDao
) {

    val notes = dao.getAllNotes()

    suspend fun insert(note: Note) {
        dao.insert(note)
    }

    suspend fun delete(note: Note) {
        dao.delete(note)
    }
}