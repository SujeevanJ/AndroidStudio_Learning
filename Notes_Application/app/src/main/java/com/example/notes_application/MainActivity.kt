package com.example.notes_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room.Room
import com.example.notes_application.data.local.NoteDatabase
import com.example.notes_application.repository.NoteRepository
import com.example.notes_application.ui_layout.screens.NotesScreen
import com.example.notes_application.ui.theme.Notes_ApplicationTheme
import com.example.notes_application.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Create Room Database
        val db = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "notes_db"
        ).build()

        // Create Repository
        val repository = NoteRepository(db.noteDao())

        // Create ViewModel
        val viewModel = NoteViewModel(repository)

        setContent {

            Notes_ApplicationTheme {

                NotesScreen(viewModel)

            }
        }
    }
}