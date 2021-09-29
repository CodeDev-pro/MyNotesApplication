package com.codedev.mynotesapplication.presentation.add_edit_notes

data class NoteTextFieldState(
    val hint: String = "",
    val text: String = "",
    val isFocused: Boolean = false,
    val isHintVisible: Boolean = false
)