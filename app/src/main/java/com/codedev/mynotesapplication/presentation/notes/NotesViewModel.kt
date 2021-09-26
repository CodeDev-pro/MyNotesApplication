package com.codedev.mynotesapplication.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codedev.mynotesapplication.domain.models.Note
import com.codedev.mynotesapplication.domain.util.NoteUseCases
import com.codedev.mynotesapplication.domain.util.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _uiEvent: Channel<UiEvent> = Channel()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _noteState = mutableStateOf(NoteState())
    val noteState: State<NoteState> = _noteState

    var latestDeletedNote: Note? = null

    fun execute(event: NoteEvents) {
        viewModelScope.launch {
            when(event) {
                is NoteEvents.ChangeOrder -> {
                    noteUseCases.getAllNotes(event.noteOrder).collectLatest {
                        _noteState.value = _noteState.value.copy(
                            notes = it
                        )
                    }
                }
                is NoteEvents.DeleteNote -> {
                    noteUseCases.deleteNote(event.note)
                    latestDeletedNote = event.note
                    _uiEvent.send(UiEvent.DeletedSuccessfully)
                }
                is NoteEvents.SearchNote -> {
                    noteUseCases.searchNote(event.title).collectLatest {
                        when(it) {
                            is Resources.Loading -> {
                                _noteState.value = noteState.value.copy(
                                    loading = true
                                )
                            }
                            is Resources.Error -> {
                                val message = it.exception.message ?: "An Unknown Error Occurred"
                                _noteState.value = noteState.value.copy(
                                    loading = false,
                                    errorMessage = message
                                )
                                _uiEvent.send(UiEvent.Error(message))
                            }
                            is Resources.Success -> {
                                _noteState.value = noteState.value.copy(
                                    loading = true,
                                    errorMessage = "",
                                    notes = it.notes
                                )
                            }
                        }
                    }
                }
                NoteEvents.RestoreNote -> {
                    latestDeletedNote?.let { noteUseCases.insertNote(it) }
                    latestDeletedNote = null
                }
            }
        }
    }

    sealed class UiEvent {
        object DeletedSuccessfully: UiEvent()
        data class Error(val message: String): UiEvent()
    }
}