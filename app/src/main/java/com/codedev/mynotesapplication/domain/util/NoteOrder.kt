package com.codedev.mynotesapplication.domain.util

sealed class NoteOrder {
    data class Title(val orderType: ListOrderType): NoteOrder()
    data class Date(val orderType: ListOrderType): NoteOrder()
    data class Color(val orderType: ListOrderType): NoteOrder()
}
