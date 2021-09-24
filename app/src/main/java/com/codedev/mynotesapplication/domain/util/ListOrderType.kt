package com.codedev.mynotesapplication.domain.util

sealed class ListOrderType {
    object AscendingOrder: ListOrderType()
    object DescendingOrder: ListOrderType()
}
