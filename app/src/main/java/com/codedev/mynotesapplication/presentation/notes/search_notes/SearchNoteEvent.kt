package com.codedev.mynotesapplication.presentation.notes.search_notes

sealed class SearchNoteEvent {
    data class ChangeTextField(val query: String): SearchNoteEvent()
}
