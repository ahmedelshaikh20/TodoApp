package com.example.todoapp.ui.screens.signinscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todoapp.R
import com.example.todoapp.navigation.Screen
import com.example.todoapp.staticImage
import com.example.todoapp.ui.components.ButtonComponent
import com.example.todoapp.ui.components.MyTextField
import com.example.todoapp.ui.components.PasswordTextField
import com.example.todoapp.ui.components.TextClickable
import com.example.todoapp.ui.screens.signupscreen.Headline
import com.example.todoapp.utils.fontsfamilys
import com.example.todoapp.viewmodel.SignInViewModel


@Composable
fun SignInScreen(
  navController: NavHostController,
  signInViewModel: SignInViewModel = hiltViewModel()
) {

  val state = signInViewModel.state
  LaunchedEffect(key1 = state.userSuccessfullyLogged) {
    if (state.userSuccessfullyLogged) {
      navController.navigate(Screen.HomeScreen.withArgs(state.currentUserInfo?.fullName.toString())) {
        popUpTo(navController.graph.id) {
          inclusive = true
        }
      }

    }
  }
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .background(colorResource(id = R.color.backgroundColor))
      .verticalScroll(rememberScrollState())
      .height(IntrinsicSize.Max),

    verticalArrangement = Arrangement.spacedBy(50.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    staticImage()
    HeadlineWithImage()
    SigninSection(Modifier.padding(horizontal = 15.dp), onEmailChange = {
      signInViewModel.signinEventTriggered(SignInUIEvent.EmailChanged(it))
    }, onPasswordChange = {
      signInViewModel.signinEventTriggered(SignInUIEvent.PasswordChanged(it))
    })
    Box(modifier = Modifier.fillMaxSize()) {


      ComposeSignInButton(
        modifier = Modifier
          .padding(15.dp)
          .align(Alignment.BottomCenter),
        textButton = stringResource(R.string.login),
        instructionText = stringResource(R.string.don_t_have_an_account),
        navText = stringResource(R.string.sign_up),
        onRegisterClick = { navController.navigate(Screen.SignUpScreen.route) },
        onSignInClicked = { signInViewModel.loginUser() }
      )
    }
  }

}

@Composable
fun HeadlineWithImage(modifier: Modifier = Modifier) {

  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(5.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Headline(stringResource(R.string.welcome_back), "")
    Image(
      painter = painterResource(id = R.drawable.signin_img),
      contentDescription = "Sign In Image",
      modifier = Modifier.size(120.dp)
    )
  }
}

@Composable
fun SigninSection(
  modifier: Modifier = Modifier,
  onPasswordChange: (String) -> (Unit),
  onEmailChange: (String) -> (Unit)
) {
  Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
    MyTextField(
      label = stringResource(R.string.email),
      placeholder = stringResource(R.string.email_placeholder),
      onValueChanged = {
        onEmailChange(it)
      })
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
      PasswordTextField(
        label = stringResource(R.string.password),
        placeholder = stringResource(R.string.password_placeholder),
        onValueChanged = {
          onPasswordChange(it)

        })
      Box(modifier = Modifier.fillMaxWidth()) {
        Text(
          text = stringResource(R.string.forgot_password),
          style = TextStyle(
            fontSize = 15.sp,
            fontFamily = fontsfamilys.poppinsFamily,
            fontWeight = FontWeight(700),
            color = colorResource(id = R.color.mainColor),
          ),
          modifier = Modifier.align(Alignment.CenterEnd)
        )
      }
    }

  }
}

@Composable
fun ComposeSignInButton(
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
    ButtonComponent(value = textButton, onSignInClicked)
    TextClickable(
      instructionText = instructionText,
      clickableText = navText,
      onClick = { onRegisterClick() })

  }
}
