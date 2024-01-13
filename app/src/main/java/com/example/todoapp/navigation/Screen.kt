package com.example.todoapp.navigation

sealed class Screen(val route: String) {


  object SplashScreen : Screen("splash_screen")
  object SignUpScreen : Screen("signup_screen")
  object SigninScreen : Screen("signin_screen")
  object HomeScreen : Screen("home_screen")

  fun withArgs(vararg args: String): String {
    return buildString {
      append(route)
      args.forEach { arg ->
        append(("/$arg"))
      }
    }
  }
}
