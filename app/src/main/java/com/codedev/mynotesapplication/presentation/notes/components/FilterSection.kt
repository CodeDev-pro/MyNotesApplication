package com.codedev.mynotesapplication.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 7.5.dp, end = 7.5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            CustomChip(
                text = "Ascending",

                onSelect = {

                })
            CustomChip(
                text = "Descending",
                onSelect = {

                })
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            CustomChip(
                text = "Title",
                onSelect = { })
            CustomChip(
                text = "Date",
                onSelect = { })
            CustomChip(text = "Color",
                onSelect = { })
        }
    }
}