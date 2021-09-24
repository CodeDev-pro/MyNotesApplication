package com.codedev.mynotesapplication.domain.usecases

import com.codedev.mynotesapplication.data.repository.NoteRepository
import com.codedev.mynotesapplication.domain.models.Note

class InsertNote constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()) {

        }
        if (note.content.isBlank()) {

        }
        repository.insertNote(note)
    }
}