package com.codedev.mynotesapplication.presentation.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codedev.mynotesapplication.ui.theme.CustomLightDarkGray
import com.codedev.mynotesapplication.ui.theme.TextLightGray
import com.codedev.mynotesapplication.ui.theme.TextWhite

@Composable
fun CustomChip(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean = false,
    onSelect: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(start = 7.5.dp, end = 7.5.dp, top = 7.5.dp, bottom = 7.5.dp)
            .clickable {
                onSelect()
            }
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(10.dp),
                color = if (selected) TextWhite else Color.Transparent
            )
            .clip(
                RoundedCornerShape(10.dp)
            )
            .background(if (selected) Color.Transparent else CustomLightDarkGray)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, style = MaterialTheme.typography.body1.copy(color = TextWhite))
    }
}