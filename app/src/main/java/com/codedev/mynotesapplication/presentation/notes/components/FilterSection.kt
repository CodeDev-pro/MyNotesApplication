package com.codedev.mynotesapplication.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codedev.mynotesapplication.domain.util.ListOrderType
import com.codedev.mynotesapplication.domain.util.NoteOrder

@Composable
fun FilterSection(
    modifier: Modifier = Modifier,
    onOrderChanged: (NoteOrder) -> Unit,
    noteOrder: NoteOrder = NoteOrder.Date(ListOrderType.DescendingOrder)
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
                    onOrderChanged(noteOrder.copy(ListOrderType.AscendingOrder))
                }, selected = noteOrder.orderType is ListOrderType.AscendingOrder
            )
            CustomChip(
                text = "Descending",
                selected = noteOrder.orderType is ListOrderType.DescendingOrder,
                onSelect = {
                    onOrderChanged(noteOrder.copy(ListOrderType.DescendingOrder))
                })
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            CustomChip(
                text = "Title",
                selected = noteOrder is NoteOrder.Title,
                onSelect = {
                    onOrderChanged(NoteOrder.Title(noteOrder.orderType))
                })
            CustomChip(
                text = "Date",
                selected = noteOrder is NoteOrder.Date,
                onSelect = { onOrderChanged(NoteOrder.Date(noteOrder.orderType)) })
            CustomChip(text = "Color",
                selected = noteOrder is NoteOrder.Color,
                onSelect = { onOrderChanged(NoteOrder.Color(noteOrder.orderType)) })
        }
    }
}