package com.codedev.mynotesapplication.presentation.notes

import com.codedev.mynotesapplication.domain.models.Note
import com.codedev.mynotesapplication.domain.util.ListOrderType
import com.codedev.mynotesapplication.domain.util.NoteOrder

data class NoteState(
    val notes: List<Note> = emptyList(),
    val loading: Boolean = false,
    val errorMessage: String = "",
    val currentPosition: Int = 0,
    val noteOrder: NoteOrder = NoteOrder.Title(ListOrderType.DescendingOrder)
)