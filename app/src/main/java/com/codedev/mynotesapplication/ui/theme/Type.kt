package com.codedev.mynotesapplication.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.codedev.mynotesapplication.R


val SegoeUi = FontFamily(
    fonts = listOf(
        Font(R.font.segoe_ui, FontWeight.Normal),
        Font(R.font.segoe_ui_bold, FontWeight.Bold),
        Font(R.font.segoe_ui_italic, FontWeight.Light),
        Font(R.font.segoe_ui_bold_italic, FontWeight.ExtraBold),
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = SegoeUi,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = TextLightGray
    ),
    h6 = TextStyle(
        fontFamily = SegoeUi,
        fontWeight = FontWeight.Bold,
        fontSize = 19.sp,
        color = TextDarkGray
    ),
    caption = TextStyle(
        fontFamily = SegoeUi,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = TextLightGray
    )
)