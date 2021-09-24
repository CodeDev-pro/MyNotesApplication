package com.codedev.mynotesapplication.domain.models

import androidx.room.Entity

@Entity
data class Note(
    val id: Int? = null,
    val title: String,
    val content: String,
    val color: Int,
    val date: Int
)
