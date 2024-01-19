package com.example.todoapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.todoapp.ui.components.BasicTextField
import com.example.todoapp.ui.components.PasswordTextField
import com.example.todoapp.ui.components.TextClickable
import com.example.todoapp.utils.SignUpUIEvent
import com.example.todoapp.viewmodel.SignUpViewModel


@Composable
fun SignUpScreen(navController: NavHostController, signUpViewModel: SignUpViewModel) {
  val registrationInfo = signUpViewModel.userRegistrationInfo.collectAsState()
  val isRegistrationDone = signUpViewModel.isRegistrationDone.collectAsState()
  LaunchedEffect(key1 = isRegistrationDone.value ){
    if (isRegistrationDone.value){
      navController.navigate(Screen.HomeScreen.withArgs(registrationInfo.value.fullName)){
        popUpTo(navController.graph.id){
          inclusive=true
        }
      }
    }
  }
  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .height(IntrinsicSize.Max)
      .background(colorResource(id = R.color.backgroundColor)),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(50.dp)

  ) {
    staticImage()
    Headline(stringResource(R.string.welcome_onboard), stringResource(R.string.welcome_message))
    Box(
      modifier = Modifier
        .fillMaxWidth(), contentAlignment = Alignment.Center
    )
    {
      Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        MyTextField(
          label = "Full Name",
          placeholder = stringResource(R.string.fullname_placeholder),
          onValueChanged = { signUpViewModel.signUpEventsTriggered(SignUpUIEvent.FullNameChanged(it)) },
          modifier = Modifier.padding(horizontal = 20.dp),
        )
        MyTextField(
          label = stringResource(id = R.string.email),
          placeholder = stringResource(id = R.string.email_placeholder),
          onValueChanged = { signUpViewModel.signUpEventsTriggered(SignUpUIEvent.EmailChanged(it)) },
          modifier = Modifier.padding(horizontal = 20.dp),

          )
        PasswordTextField(
          label = stringResource(id = R.string.password),
          placeholder = stringResource(id = R.string.password_placeholder),
          onValueChanged = { signUpViewModel.signUpEventsTriggered(SignUpUIEvent.PasswordChanged(it)) },
          modifier = Modifier.padding(horizontal = 20.dp),
        )


      }
    }
    Box(modifier = Modifier.fillMaxHeight()) {
      ComposeSignUpButton(
        textButton = stringResource(R.string.register),
        instructionText = stringResource(R.string.already_have_an_account),
        navText = stringResource(R.string.sign_in),
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
fun Headline(title: String, par: String, modifier: Modifier = Modifier) {
  Column(
    modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(5.dp)
  ) {
    BoldTextField(value = title, size = 22)
      BasicTextField(value = par)

  }
}

@Composable
fun ComposeSignUpButton(
  textButton: String,
  instructionText: String,
  navText: String,
  onRegisterClick: () -> (Unit),
  onSignInClicked: () -> (Unit),
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
  ) {
    ButtonComponent(value = textButton, onRegisterClick)

      TextClickable(
        instructionText = instructionText,
        clickableText = navText,
        onClick = { onSignInClicked() })

  }
}

