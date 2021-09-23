package com.codedev.mynotesapplication.domain.repository

import com.codedev.mynotesapplication.data.datasource.NoteDao
import com.codedev.mynotesapplication.data.repository.NoteRepository
import com.codedev.mynotesapplication.domain.models.Note
import com.codedev.mynotesapplication.domain.util.Resources
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    override suspend fun searchNote(title: String): Flow<Resources> = flow {
        emit(Resources.Loading)
        try {
            delay(5000)
            val notes = noteDao.searchNote(title)
            emit(Resources.Success(notes))
        }catch (e: Exception) {
            emit(Resources.Error(e))
        }
    }

    override suspend fun getNote(id: Int): Note {
        return noteDao.getNote(id)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insert(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }
}