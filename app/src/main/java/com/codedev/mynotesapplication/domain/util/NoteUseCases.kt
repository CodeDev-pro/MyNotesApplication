package com.codedev.mynotesapplication.domain.util

import com.codedev.mynotesapplication.domain.usecases.*

data class NoteUseCases(
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val deleteNote: DeleteNote,
    val insertNote: InsertNote,
    val searchNote: SearchNote,
    val updateNote: UpdateNote
)