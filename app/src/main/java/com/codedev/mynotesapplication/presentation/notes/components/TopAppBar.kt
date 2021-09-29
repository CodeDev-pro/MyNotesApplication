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
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codedev.mynotesapplication.R
import com.codedev.mynotesapplication.ui.theme.*

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    onSearchClicked: () -> Unit
) {
    Box(modifier = modifier.fillMaxWidth().background(TextDarkGray)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(
                text = "Notes",
                style = MaterialTheme.typography.h6.copy(color = TextWhite, fontSize = 25.sp)
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .clickable { onSearchClicked() }
                    .background(CustomLightDarkGray)
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    modifier = Modifier.padding(10.dp).size(24.dp),
                    contentDescription = "Search Icon",
                    tint = TextWhite
                )
            }
        }
    }
}