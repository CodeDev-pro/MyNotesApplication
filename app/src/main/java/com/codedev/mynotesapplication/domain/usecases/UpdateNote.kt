package com.codedev.mynotesapplication.domain.usecases

import com.codedev.mynotesapplication.data.repository.NoteRepository
import com.codedev.mynotesapplication.domain.models.Note

class UpdateNote constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.updateNote(note)
    }
}