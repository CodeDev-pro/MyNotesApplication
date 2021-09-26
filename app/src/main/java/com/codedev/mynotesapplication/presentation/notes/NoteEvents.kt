package com.codedev.mynotesapplication.presentation.notes

import com.codedev.mynotesapplication.domain.models.Note
import com.codedev.mynotesapplication.domain.util.NoteOrder

sealed class NoteEvents {
    data class DeleteNote(val note: Note): NoteEvents()
    data class SearchNote(val title: String): NoteEvents()
    data class ChangeOrder(val noteOrder: NoteOrder): NoteEvents()
    object RestoreNote: NoteEvents()
}