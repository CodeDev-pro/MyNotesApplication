package com.codedev.mynotesapplication.presentation.search_notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codedev.mynotesapplication.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: NoteRepository
): ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state : State<SearchState> = _state

    val execute: (SearchEvents) -> Unit = { event ->
        viewModelScope.launch {
            when(event) {
                is SearchEvents.SearchNote -> {
                    repository.searchNote(event.query).collectLatest {
                        _state.value = _state.value.copy(data = it)
                    }
                }
                is SearchEvents.DeleteNote -> {
                    repository.deleteNote(event.note)
                }
            }
        }
    }
}