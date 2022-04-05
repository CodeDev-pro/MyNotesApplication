package com.codedev.mynotesapplication.presentation.notes

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codedev.mynotesapplication.domain.models.Note
import com.codedev.mynotesapplication.domain.util.ListOrderType
import com.codedev.mynotesapplication.domain.util.NoteOrder
import com.codedev.mynotesapplication.domain.util.NoteUseCases
import com.codedev.mynotesapplication.domain.util.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
    var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(ListOrderType.AscendingOrder))
    }

    fun execute(event: NoteEvents) {
        viewModelScope.launch {
            when(event) {
                is NoteEvents.ChangeOrder -> {
                    if(noteState.value.noteOrder::class == event.noteOrder::class
                        && noteState.value.noteOrder.orderType == event.noteOrder.orderType) {
                        return@launch
                    }
                    getNotes(event.noteOrder)
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

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getAllNotes(noteOrder).onEach {
            _noteState.value = noteState.value.copy(
                notes = it,
                noteOrder = noteOrder
            )
        }.launchIn(viewModelScope)
    }

    sealed class UiEvent {
        object DeletedSuccessfully: UiEvent()
        data class Error(val message: String): UiEvent()
    }
}