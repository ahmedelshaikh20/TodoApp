package com.example.todoapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.staticImage
import com.example.todoapp.utils.fontsfamilys


@Composable
fun SplashScreen() {

  Column(
    Modifier
      .fillMaxSize()
      .padding(top = 100.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    staticImage()
    Image(
      painter = painterResource(id = R.drawable.splash_background),
      contentDescription = "Background Image",
      Modifier
        .size(170.dp)
    )
    Text(
      modifier = Modifier.padding(top = 20.dp),
      text = "Get things done with TODO",
      style = TextStyle(
        fontSize = 22.sp,
        fontFamily = fontsfamilys.poppinsFamily,
        fontWeight = FontWeight(700),
        color = Color(0xE5000000),
        textAlign = TextAlign.Center,
      )
    )
    Box(modifier = Modifier.fillMaxSize()) {
      Button(modifier = Modifier
        .padding(bottom = 10.dp, start = 20.dp, end = 20.dp)
        .fillMaxWidth()
        .align(Alignment.BottomCenter),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.buttonColor)),
        shape = RoundedCornerShape(5.dp),
        onClick = { /*TODO*/ }) {
        Text(
          text = "Get Started",
          style = TextStyle(
            fontSize = 20.sp,
            fontFamily = fontsfamilys.poppinsFamily,
            fontWeight = FontWeight.Bold,
            color = Color(0xE5000000),
            textAlign = TextAlign.Center,
          )
        )
      }
    }


  }


}
