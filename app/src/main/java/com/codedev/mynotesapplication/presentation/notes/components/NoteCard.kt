package com.codedev.mynotesapplication.presentation.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.codedev.mynotesapplication.domain.models.Note

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: Note
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(7.5.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(note.color))
            .padding(vertical = 15.dp, horizontal = 10.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth(0.6f)){
                Text(
                    text = note.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.h6
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = note.content,
                overflow = TextOverflow.Ellipsis,
                maxLines = 7,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = note.date.toString(),
                modifier = Modifier.align(Alignment.End),
                style = MaterialTheme.typography.caption
            )
        }
    }
}