package com.codedev.mynotesapplication.presentation.search_notes

import com.codedev.mynotesapplication.domain.models.Note

data class SearchState(
    val data: List<Note> = emptyList(),
    val loading: Boolean = false,
    val error: String = "",
    val query: String = ""
)