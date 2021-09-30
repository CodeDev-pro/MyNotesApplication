package com.codedev.mynotesapplication.presentation.add_edit_notes

data class NoteTextFieldState(
    val hint: String = "",
    val text: String = "",
    val isHintVisible: Boolean = true
)