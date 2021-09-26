package com.codedev.mynotesapplication.presentation.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.codedev.mynotesapplication.R
import com.codedev.mynotesapplication.ui.theme.TextLightGray
import com.codedev.mynotesapplication.ui.theme.TextWhite

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Notes",
                style = MaterialTheme.typography.h6
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(TextLightGray)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search Icon",
                        tint = TextWhite
                    )
                }
            }
        }
    }
}