package com.codedev.mynotesapplication.presentation.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codedev.mynotesapplication.domain.models.Note
import com.codedev.mynotesapplication.ui.theme.RedOrange
import com.codedev.mynotesapplication.ui.theme.TextDarkGray
import com.codedev.mynotesapplication.ui.theme.TextLightGray

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: Note,
    onClick: (Note) -> Unit,
    onDeleteClick: (Note) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(7.5.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                onClick(note)
            }
            .background(Color(note.color))
            .padding(vertical = 15.dp, horizontal = 10.dp)
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth(0.6f)) {
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
                style = MaterialTheme.typography.caption,
                fontSize = 15.sp,
                color = TextDarkGray
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = note.date.toString(),
                modifier = Modifier.align(Alignment.End),
                style = MaterialTheme.typography.caption,
                fontSize = 15.sp,
                color = TextDarkGray
            )
        }
        Box(modifier = Modifier.align(Alignment.TopEnd).padding(end = 7.5.dp)) {
            IconButton(onClick = { onDeleteClick(note) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "delete Icon", Modifier.size(24.dp))
            }
        }
    }
}