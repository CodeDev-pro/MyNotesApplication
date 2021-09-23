package com.codedev.mynotesapplication.data.repository

import com.codedev.mynotesapplication.data.datasource.NoteDao
import com.codedev.mynotesapplication.domain.models.Note
import com.codedev.mynotesapplication.domain.util.Resources
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAllNotes() : Flow<List<Note>>

    suspend fun searchNote(title: String): Flow<Resources>

    suspend fun getNote(id: Int): Note

    suspend fun deleteNote(note: Note)

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

}