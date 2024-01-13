package com.example.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.screens.HomeScreen
import com.example.todoapp.ui.screens.SignInScreen
import com.example.todoapp.ui.screens.SignUpScreen
import com.example.todoapp.ui.screens.SplashScreen

@Composable
fun Navigation() {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
    composable(Screen.SplashScreen.route) { SplashScreen(navController) }
    composable(Screen.HomeScreen.route) { HomeScreen(navController) }
    composable(Screen.SigninScreen.route) { SignInScreen(navController) }
    composable(Screen.SignUpScreen.route) { SignUpScreen(navController) }

  }
}
