package com.codedev.mynotesapplication.presentation.add_edit_notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codedev.mynotesapplication.ui.theme.TextDarkGray
import com.codedev.mynotesapplication.ui.theme.noteColors

@Composable
fun PickColorSection(
    modifier: Modifier = Modifier,
    selectedColor: Color,
    onChangeColor: (Color) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        noteColors.forEach { color ->
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .shadow(5.dp, CircleShape)
                    .clip(CircleShape)
                    .background(color)
                    .border(
                        1.dp, if(selectedColor == color) TextDarkGray else Color.Transparent,  CircleShape
                    )
                    .clickable {
                        onChangeColor(color)
                    }
            )
        }
    }
}