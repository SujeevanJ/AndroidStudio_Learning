package com.example.notes_application.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes_application.data.local.Note
import com.example.notes_application.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(
    private val repository: NoteRepository
) : ViewModel() {

    val notes = repository.notes

    fun addNote(title: String) {

        viewModelScope.launch {

            repository.insert(
                Note(title = title)
            )
        }
    }

    fun deleteNote(note: Note) {

        viewModelScope.launch {

            repository.delete(note)
        }
    }
}