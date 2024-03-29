package com.example.project.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project.components.screens.ChatScreen
import com.example.project.components.screens.HomeScreen
import com.example.project.components.screens.StartScreen

@Composable
fun MainNavigation() {

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = START_SCREEN) {
        composable(START_SCREEN) {
            StartScreen(navHostController)
        }
        composable(HOME_SCREEN) {
            HomeScreen(navHostController)
        }
        composable(CHAT_SCREEN) {
            ChatScreen(navHostController)
        }
    }

}

const val START_SCREEN = "Start screen"
const val HOME_SCREEN = "Home screen"
const val CHAT_SCREEN = "Chat screen"