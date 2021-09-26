package com.codedev.mynotesapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codedev.mynotesapplication.presentation.notes.NoteScreen
import com.codedev.mynotesapplication.ui.theme.MyNotesApplicationTheme
import com.codedev.mynotesapplication.ui.theme.TextDarkGray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNotesApplicationTheme {
                Surface(color = TextDarkGray) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home"){
                        composable(route = "home") {
                            NoteScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}