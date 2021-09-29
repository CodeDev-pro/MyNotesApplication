package com.codedev.mynotesapplication.presentation.add_edit_notes

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvents {
    data class EnteredTitle(val title: String): AddEditNoteEvents()
    data class EnteredContent(val content: String): AddEditNoteEvents()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditNoteEvents()
    data class ChangeContentFocus(val focusState: FocusState): AddEditNoteEvents()
    data class ChangeColor(val color: Int): AddEditNoteEvents()
    object SaveNote: AddEditNoteEvents()
}
