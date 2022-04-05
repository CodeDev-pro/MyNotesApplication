package com.codedev.mynotesapplication.presentation

sealed class Screen(
    val route: String
) {
    object AddEditNoteScreen : Screen("add_edit_note_screen")
    object NoteScreen : Screen("note_screen")
    object SearchNoteScreen: Screen("search_note")
}
