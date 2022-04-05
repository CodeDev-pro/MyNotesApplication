package com.codedev.mynotesapplication.domain.repository

import com.codedev.mynotesapplication.data.datasource.NoteDao
import com.codedev.mynotesapplication.data.repository.NoteRepository
import com.codedev.mynotesapplication.domain.models.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    override fun searchNote(query: String): Flow<List<Note>> {
        val dbQuery = "%${query.replace(' ', '%')}%"
        return noteDao.searchNote(dbQuery)
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