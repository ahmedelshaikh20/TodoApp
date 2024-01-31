package com.example.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapp.ui.screens.homescreen.HomeScreen
import com.example.todoapp.ui.screens.signinscreen.SignInScreen
import com.example.todoapp.ui.screens.signupscreen.SignUpScreen
import com.example.todoapp.ui.screens.splashscreen.SplashScreen

@Composable
fun Navigation() {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
    composable(Screen.SplashScreen.route) { SplashScreen(navController) }
    composable(
      Screen.HomeScreen.route + "/{user_name}", arguments = listOf(navArgument("user_name") {
        type = NavType.StringType
        nullable = false
        defaultValue = ""
      })
    ) { entry ->
      HomeScreen(navController, userName = entry.arguments?.getString("user_name"))
    }
    composable(Screen.SigninScreen.route) { SignInScreen(navController) }
    composable(Screen.SignUpScreen.route) { SignUpScreen(navController) }

  }
}
