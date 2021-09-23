package com.codedev.mynotesapplication.domain.util

import com.codedev.mynotesapplication.domain.models.Note

sealed class Resources {
    data class Success(val notes: List<Note>): Resources()
    data class Error(val exception: Exception): Resources()
    object Loading: Resources()
}
