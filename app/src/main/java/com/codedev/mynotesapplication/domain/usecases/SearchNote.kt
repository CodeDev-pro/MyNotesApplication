package com.codedev.mynotesapplication.domain.usecases

import com.codedev.mynotesapplication.data.repository.NoteRepository
import com.codedev.mynotesapplication.domain.util.Resources
import kotlinx.coroutines.flow.Flow

class SearchNote constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(title: String) : Flow<Resources> =
        repository.searchNote(title)
}