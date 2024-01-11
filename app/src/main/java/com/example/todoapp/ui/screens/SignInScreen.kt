package com.example.todoapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.utils.fontsfamilys


@Composable
fun SignInScreen() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.spacedBy(50.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    headlineWithImage()
    signinSection(Modifier.padding(horizontal = 15.dp))
    composeButton(
      textButton = "Login",
      instructionText = "Don’t have an account ? ",
      navText = "Sign Up"
    )
  }


}

@Composable
fun headlineWithImage(modifier: Modifier = Modifier) {

  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(5.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    headline("Welcome Back")
    Image(
      painter = painterResource(id = R.drawable.signin_img),
      contentDescription = "Sign In Image",
      modifier = Modifier.size(120.dp)
    )
  }
}

@Composable
fun signinSection(modifier: Modifier = Modifier) {
  Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
    combinedTextField(label = "Email", placeholder = "ahmed@gmail.com")
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
      combinedTextField(label = "Password", placeholder = "**************")
      Box(modifier = Modifier.fillMaxWidth()) {
        Text(
          text = "Forgot Password?",
          style = TextStyle(
            fontSize = 15.sp,
            fontFamily = fontsfamilys.poppinsFamily,
            fontWeight = FontWeight(700),
            color = colorResource(id = R.color.buttonColor),
          ),
          modifier = Modifier.align(Alignment.CenterEnd)
        )
      }
    }

  }
}
