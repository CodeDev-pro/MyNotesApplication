package com.codedev.mynotesapplication.presentation.add_edit_notes

import android.text.format.DateUtils
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codedev.mynotesapplication.domain.models.Note
import com.codedev.mynotesapplication.domain.usecases.InsertNote
import com.codedev.mynotesapplication.domain.util.NoteUseCases
import com.codedev.mynotesapplication.ui.theme.noteColors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val useCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _noteTitle = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter title..."
        )
    )
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter content..."
        )
    )
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableStateOf<Int>(noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor
    var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let {
            if(it != -1) {
                viewModelScope.launch {
                    useCases.getNote(it).also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = noteTitle.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvents) {
        when (event) {
            is AddEditNoteEvents.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.title
                )
            }
            is AddEditNoteEvents.ChangeColor -> {
                _noteColor.value = event.color
            }
            is AddEditNoteEvents.ChangeContentFocus -> {
                Log.d("TAG", "onEvent: change focus ${event.focusState}")
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = _noteContent.value.text.isBlank()
                )
            }
            is AddEditNoteEvents.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = _noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvents.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.content
                )
            }
            AddEditNoteEvents.SaveNote -> {
                viewModelScope.launch {
                    try {
                        useCases.insertNote(
                            Note(
                                id = currentNoteId,
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                color = noteColor.value,
                                date = 1000
                            )
                        )
                        _uiEvent.send(UiEvent.SavedSuccessfully)
                    } catch (e: Exception) {
                        _uiEvent.send(
                            UiEvent.Error(
                                if (e is InsertNote.InvalidNoteException) e.message
                                    ?: "Unknown Error" else "An Unknown Error Occurred"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        object SavedSuccessfully : UiEvent()
        data class Error(val message: String) : UiEvent()
    }
}