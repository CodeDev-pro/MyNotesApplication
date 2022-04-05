package com.codedev.mynotesapplication.presentation.search_notes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.codedev.mynotesapplication.R
import com.codedev.mynotesapplication.ui.theme.DarkGray
import com.codedev.mynotesapplication.ui.theme.TextWhite

@Composable
fun SearchAppBar(
    value: String = "",
    onValueChanged: (String) -> Unit,
    onSubmit: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.DarkGray)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = TextWhite.copy(0.5f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier.weight(1f),
            ) {
                BasicTextField(
                    value = value,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        onValueChanged(it)
                    },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.body1,
                    keyboardActions = KeyboardActions(onSearch = {
                        onSubmit(value)
                    }),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    cursorBrush = SolidColor(DarkGray)
                )
                if(value == "") {
                    Text(
                        text = "Search...",
                        style = MaterialTheme.typography.body1.copy(color = TextWhite.copy(0.5f)),
                    )
                }
            }
        }
    }
}