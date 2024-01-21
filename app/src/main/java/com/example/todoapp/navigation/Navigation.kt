package com.example.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapp.ui.screens.HomeScreen
import com.example.todoapp.ui.screens.SignInScreen
import com.example.todoapp.ui.screens.SignUpScreen
import com.example.todoapp.ui.screens.SplashScreen
import com.example.todoapp.viewmodel.HomeViewModel
import com.example.todoapp.viewmodel.SignInViewModel
import com.example.todoapp.viewmodel.SignUpViewModel
import com.example.todoapp.viewmodel.SplashViewModel

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
