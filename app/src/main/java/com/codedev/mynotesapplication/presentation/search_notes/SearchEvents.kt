package com.codedev.mynotesapplication.presentation.search_notes

import com.codedev.mynotesapplication.domain.models.Note

sealed class SearchEvents {
    class SearchNote(val query: String): SearchEvents()
    class DeleteNote(val note: Note) : SearchEvents() {

    }
}