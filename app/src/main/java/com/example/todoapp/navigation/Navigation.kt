package com.example.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.screens.HomeScreen
import com.example.todoapp.ui.screens.SignInScreen
import com.example.todoapp.ui.screens.SignUpScreen
import com.example.todoapp.ui.screens.SplashScreen
import com.example.todoapp.viewmodel.SignUpViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun Navigation(signUpViewModel: SignUpViewModel = viewModel()) {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
    composable(Screen.SplashScreen.route) { SplashScreen(navController) }
    composable(Screen.HomeScreen.route) { HomeScreen(navController) }
    composable(Screen.SigninScreen.route) { SignInScreen(navController) }
    composable(Screen.SignUpScreen.route) { SignUpScreen(navController , signUpViewModel) }

  }
}
