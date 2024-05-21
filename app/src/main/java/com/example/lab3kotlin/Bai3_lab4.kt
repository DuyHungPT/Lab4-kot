package com.example.lab3kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class Note(
    val title: String,
    val isExpanded: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(note: Note, onExpand: (Boolean) -> Unit) {
    var isExpanded by remember { mutableStateOf(note.isExpanded) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = note.title)
            IconButton(onClick = {
                isExpanded = !isExpanded
                onExpand(isExpanded)
            }) {
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Collapse" else "Expand"
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteList(notes: List<Note>, onExpand: (Int, Boolean) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(notes.size) { index ->
            NoteItem(note = notes[index], onExpand = { isExpanded ->
                onExpand(index, isExpanded)
            })
        }
    }
}

@Composable
fun NoteApp() {
    var notes by remember {
        mutableStateOf(
            listOf(
                Note(title = "Note 1"),
                Note(title = "Note 2"),
                Note(title = "Note 3"),
                Note(title = "Note 4"),
                Note(title = "Note 5")
            )
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Handle add note action
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Note")
            }
        }
    ) { paddingValues ->
        NoteList(
            notes = notes,
            onExpand = { index, isExpanded ->
                notes = notes.toMutableList().apply {
                    this[index] = this[index].copy(isExpanded = isExpanded)
                }
            },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

class Bai3_lab4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteApp()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaulttPrevieww() {
    NoteApp()
}
