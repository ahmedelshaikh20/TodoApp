package com.example.todoapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.R
import com.example.todoapp.navigation.Screen
import com.example.todoapp.staticImage
import com.example.todoapp.ui.components.BoldTextField
import com.example.todoapp.ui.components.NormalButton
import com.example.todoapp.ui.components.NormalTextField
import com.example.todoapp.utils.fontsfamilys


@Composable
fun SplashScreen(navController: NavHostController) {

  Column(
    Modifier
      .fillMaxSize()
      .background(colorResource(id = R.color.backgroundColor)),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    staticImage()
    Image(
      painter = painterResource(id = R.drawable.splash_background),
      contentDescription = "Background Image",
      Modifier
        .fillMaxWidth()
        .heightIn(min = 120.dp)
    )
    BoldTextField(value = "Get things done with TODO", modifier = Modifier.padding(20.dp))

    Box(modifier = Modifier.fillMaxSize()) {
      NormalButton(onClick = {
        navController.navigate(Screen.SignUpScreen.route)
      }, modifier = Modifier
        .padding(15.dp)
        .align(Alignment.BottomCenter))
    }


  }


}
