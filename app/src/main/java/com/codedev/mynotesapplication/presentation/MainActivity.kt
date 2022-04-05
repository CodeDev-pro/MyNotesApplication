package com.codedev.mynotesapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codedev.mynotesapplication.presentation.add_edit_notes.AddNoteScreen
import com.codedev.mynotesapplication.presentation.notes.NoteScreen
import com.codedev.mynotesapplication.presentation.search_notes.Search
import com.codedev.mynotesapplication.ui.theme.MyNotesApplicationTheme
import com.codedev.mynotesapplication.ui.theme.TextDarkGray
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNotesApplicationTheme {
                Surface(color = TextDarkGray) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NoteScreen.route
                    ) {
                        composable(route = Screen.NoteScreen.route) {
                            NoteScreen(
                                navController = navController
                            )
                        }
                        composable(
                            route = Screen.AddEditNoteScreen.route + "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument("noteId") {
                                    defaultValue = -1
                                    type = NavType.IntType
                                },
                                navArgument("noteColor") {
                                    defaultValue = -1
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            AddNoteScreen(
                                navController = navController,
                                noteColor = backStackEntry.arguments?.getInt("noteColor") ?: -1
                            )
                        }
                        composable(route = Screen.SearchNoteScreen.route) {
                            Search()
                        }
                    }
                }
            }
        }
    }
}