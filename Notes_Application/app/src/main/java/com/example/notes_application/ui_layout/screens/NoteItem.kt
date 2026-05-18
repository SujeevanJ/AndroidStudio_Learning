package com.example.notes_application.ui_layout.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notes_application.data.local.Note

@Composable
fun NoteItem(
    note: Note,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Text(text = note.title)

            Button(
                onClick = onDelete
            ) {

                Text("Delete")
            }
        }
    }
}