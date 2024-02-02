package com.example.todoapp.ui.screens.splashscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todoapp.R
import com.example.todoapp.navigation.Screen
import com.example.todoapp.staticImage
import com.example.todoapp.ui.components.BoldTextField
import com.example.todoapp.viewmodel.SplashViewModel


@Composable
fun SplashScreen(
  navController: NavHostController,
  splashViewModel: SplashViewModel = hiltViewModel()
) {
  val state = splashViewModel.state.collectAsState()

  LaunchedEffect(key1 = state.value.isLogged) {
    if (state.value.isLogged) {
      //Do the navigation with user name
      Log.d("User Name", state.value.currentUserName.toString())
      navController.navigate(Screen.HomeScreen.withArgs(state.value.currentUserName.toString())) {
        popUpTo(navController.graph.id) {
          inclusive = true
        }
      }
    } else {
      navController.navigate(Screen.SignUpScreen.route)
    }
  }

  Column(
    Modifier
      .fillMaxSize()
      .background(colorResource(id = R.color.backgroundColor)),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    staticImage()
    Spacer(modifier = Modifier.heightIn(min = 200.dp))
    Image(
      painter = painterResource(id = R.drawable.splash_background),
      contentDescription = "Background Image",
      Modifier
        .fillMaxWidth()
        .heightIn(min = 200.dp)
    )
    BoldTextField(
      value = stringResource(R.string.intro_message),
      size = 22.sp,
      modifier = Modifier.padding(20.dp)
    )


  }


}
