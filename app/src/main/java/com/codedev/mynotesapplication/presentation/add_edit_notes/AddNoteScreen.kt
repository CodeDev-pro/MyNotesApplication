package com.codedev.mynotesapplication.presentation.add_edit_notes

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.codedev.mynotesapplication.presentation.Screen
import com.codedev.mynotesapplication.presentation.add_edit_notes.components.PickColorSection
import com.codedev.mynotesapplication.presentation.add_edit_notes.components.TopAppBar
import com.codedev.mynotesapplication.presentation.add_edit_notes.components.TransparentTextField
import com.codedev.mynotesapplication.ui.theme.CustomLightDarkGray
import com.codedev.mynotesapplication.ui.theme.TextDarkGray
import com.codedev.mynotesapplication.ui.theme.noteColors
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val noteTitleState = viewModel.noteTitle.value
    val noteContentState = viewModel.noteContent.value
    val scope = rememberCoroutineScope()

    val noteBackgroundAnimation = remember {
        Animatable(Color(if(noteColor != -1) noteColor else viewModel.noteColor.value ))
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when(event) {
                is AddEditNoteViewModel.UiEvent.SavedSuccessfully -> {
                    navController.navigate(Screen.NoteScreen.route){
                        popUpTo(Screen.NoteScreen.route){ inclusive = true }
                    }
                }
                is AddEditNoteViewModel.UiEvent.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        backgroundColor = noteBackgroundAnimation.value
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .background(Color.Transparent)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            TopAppBar()
            Spacer(modifier = Modifier.height(10.dp))
            PickColorSection(
                selectedColor = noteBackgroundAnimation.value,
                onChangeColor = {
                    scope.launch {
                        noteBackgroundAnimation.animateTo(
                            targetValue = Color(it.toArgb()),
                            animationSpec = tween(durationMillis = 500),
                        )
                    }
                    viewModel.onEvent(AddEditNoteEvents.ChangeColor(it.toArgb()))
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            TransparentTextField(
                textStyle = MaterialTheme.typography.h6.copy(fontSize = 33.sp, color = CustomLightDarkGray),
                singleLine = true,
                hint = "Enter Title...",
                onTextChange = {
                    viewModel.onEvent(AddEditNoteEvents.EnteredTitle(it))
                },
                value = noteTitleState.text
            )
            Spacer(modifier = Modifier.height(15.dp))
            TransparentTextField(
                textStyle = MaterialTheme.typography.body1.copy(fontSize = 22.sp, color = CustomLightDarkGray),
                singleLine = false,
                hint = "Enter Content...",
                onTextChange = {
                    viewModel.onEvent(AddEditNoteEvents.EnteredContent(it))
                },
                value = noteContentState.text
            )
        }
    }
}