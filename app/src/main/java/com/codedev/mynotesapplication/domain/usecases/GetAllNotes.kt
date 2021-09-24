package com.codedev.mynotesapplication.domain.usecases

import com.codedev.mynotesapplication.data.repository.NoteRepository
import com.codedev.mynotesapplication.domain.models.Note
import com.codedev.mynotesapplication.domain.util.ListOrderType
import com.codedev.mynotesapplication.domain.util.NoteOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllNotes constructor(
    private val repository: NoteRepository
) {

    operator fun invoke(noteOrder: NoteOrder) : Flow<List<Note>> {
        return repository.getAllNotes().map { notes ->
            when(noteOrder) {
                is NoteOrder.Date -> {
                    when(noteOrder.orderType) {
                        is ListOrderType.AscendingOrder -> {
                            notes.sortedBy { it.date }
                        }
                        is ListOrderType.DescendingOrder -> {
                            notes.sortedByDescending { it.date }
                        }
                    }
                }
                is NoteOrder.Color -> {
                    when(noteOrder.orderType) {
                        is ListOrderType.AscendingOrder -> {
                            notes.sortedBy { it.color }
                        }
                        is ListOrderType.DescendingOrder -> {
                            notes.sortedByDescending { it.color }
                        }
                    }
                }
                is NoteOrder.Title -> {
                    when(noteOrder.orderType) {
                        is ListOrderType.AscendingOrder -> {
                            notes.sortedBy { it.title }
                        }
                        is ListOrderType.DescendingOrder -> {
                            notes.sortedByDescending { it.title }
                        }
                    }
                }
            }
        }

    }
}