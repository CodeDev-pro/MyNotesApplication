package com.codedev.mynotesapplication.domain.util

sealed class NoteOrder(
    val orderType: ListOrderType
) {
    class Title(orderType: ListOrderType): NoteOrder(orderType = orderType)
    class Date(orderType: ListOrderType): NoteOrder(orderType = orderType)
    class Color(orderType: ListOrderType): NoteOrder(orderType = orderType)

    fun copy(orderType: ListOrderType): NoteOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Color -> Color(orderType)
            is Date -> Date(orderType)
        }
    }
}
