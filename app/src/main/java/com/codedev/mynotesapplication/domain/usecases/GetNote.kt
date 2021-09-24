package com.codedev.mynotesapplication.domain.usecases

import com.codedev.mynotesapplication.data.repository.NoteRepository
import com.codedev.mynotesapplication.domain.models.Note

class GetNote constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int) : Note {
        return repository.getNote(id)
    }
}