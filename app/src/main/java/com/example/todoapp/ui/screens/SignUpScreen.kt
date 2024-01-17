package com.example.todoapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todoapp.R
import com.example.todoapp.navigation.Screen
import com.example.todoapp.staticImage
import com.example.todoapp.ui.components.BoldTextField
import com.example.todoapp.ui.components.ButtonComponent
import com.example.todoapp.ui.components.MyTextField
import com.example.todoapp.ui.components.NormalTextField
import com.example.todoapp.ui.components.PasswordTextField
import com.example.todoapp.ui.components.TextClickable
import com.example.todoapp.utils.SignUpUIEvent
import com.example.todoapp.viewmodel.SignUpViewModel


@Composable
fun SignUpScreen(navController: NavHostController, signUpViewModel: SignUpViewModel) {
  val isRegistrationDone = signUpViewModel.isRegistrationDone.collectAsState()
  LaunchedEffect(key1 = isRegistrationDone.value ){
    if (isRegistrationDone.value){
      navController.navigate(Screen.HomeScreen.route)
    }
  }
  val scrollStateVertical = rememberScrollState()
  Column(
    modifier = Modifier
      .fillMaxSize()
      .scrollable(scrollStateVertical, Orientation.Vertical, enabled = true)
      .background(colorResource(id = R.color.backgroundColor)),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(50.dp)

  ) {
    staticImage()
    headline("Welcome Onboard!", "Lets help you in completing your tasks")
    Box(
      modifier = Modifier
        .fillMaxWidth(), contentAlignment = Alignment.Center
    )
    {
      Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        MyTextField(
          label = "Full Name",
          placeholder = "Ahmed Salah",
          onValueChanged = { signUpViewModel.signUpEventsTriggered(SignUpUIEvent.FullNameChanged(it)) },
          modifier = Modifier.padding(horizontal = 20.dp),
        )
        MyTextField(
          label = "Email",
          placeholder = "ahmed@gmail.com",
          onValueChanged = { signUpViewModel.signUpEventsTriggered(SignUpUIEvent.EmailChanged(it)) },
          modifier = Modifier.padding(horizontal = 20.dp),

          )
        PasswordTextField(
          label = "Password",
          placeholder = "**************",
          onValueChanged = { signUpViewModel.signUpEventsTriggered(SignUpUIEvent.PasswordChanged(it)) },
          modifier = Modifier.padding(horizontal = 20.dp),
        )


      }
    }
    Box(modifier = Modifier.fillMaxHeight()) {
      ComposeSignUpButton(
        stringResource(R.string.register),
        stringResource(R.string.already_have_an_account),
        stringResource(R.string.sign_in),
        modifier = Modifier
          .padding(15.dp)
          .fillMaxWidth()
          .align(Alignment.BottomCenter),
        onRegisterClick = {
          signUpViewModel.signUp()
        },
        onSignInClicked = {
          navController.navigate(Screen.SigninScreen.route)
        }
      )
    }


  }
}


@Composable
fun headline(title: String, par: String? = null, modifier: Modifier = Modifier) {
  Column(
    modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(5.dp)
  ) {
    BoldTextField(value = title)
    if (par != null) {
      NormalTextField(value = par)
    }
  }
}

@Composable
fun ComposeSignUpButton(
  textButton: String,
  instructionText: String? = null,
  navText: String? = null,
  onRegisterClick: () -> (Unit),
  onSignInClicked: () -> (Unit),
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
  ) {
    ButtonComponent(value = textButton, onRegisterClick)
    if (instructionText != null && navText != null) {
      TextClickable(
        instructionText = instructionText,
        clickableText = navText,
        onClick = { onSignInClicked() })
    }
  }
}

