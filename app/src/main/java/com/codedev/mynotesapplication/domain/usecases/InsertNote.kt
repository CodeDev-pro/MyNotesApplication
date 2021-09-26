package com.codedev.mynotesapplication.domain.usecases

import com.codedev.mynotesapplication.data.repository.NoteRepository
import com.codedev.mynotesapplication.domain.models.Note
import kotlin.jvm.Throws

class InsertNote constructor(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()) {
            throw InvalidNoteException("Note title must not be empty")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("Note content must not be empty")
        }
        repository.insertNote(note)
    }

    class InvalidNoteException(message: String) : Exception(message)
}