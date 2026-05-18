package com.example.notes_application.ui_layout.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notes_application.data.local.Note
import com.example.notes_application.viewmodel.NoteViewModel

@Composable
fun NotesScreen(viewModel: NoteViewModel) {

    var text by remember {
        mutableStateOf("")
    }

    val notes by viewModel.notes.collectAsState(
        initial = emptyList()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = {
                Text("Enter Note")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {

                viewModel.addNote(text)

                text = ""
            }
        ) {
            Text("Add Note")
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(notes) { note ->

                NoteItem(
                    note = note,
                    onDelete = {
                        viewModel.deleteNote(note)
                    }
                )
            }
        }
    }
}