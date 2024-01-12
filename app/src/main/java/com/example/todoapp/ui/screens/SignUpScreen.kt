package com.example.todoapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.staticImage
import com.example.todoapp.utils.fontsfamilys


@Composable
fun SignUpScreen() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.spacedBy(50.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    staticImage()
    headline("Welcome Onboard!", "Lets help you in completing your tasks")
    Box(
      modifier = Modifier
        .fillMaxWidth(), contentAlignment = Alignment.Center
    )
    {
      Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        combinedTextField(
          label = "Full Name",
          placeholder = "Ahmed Salah",
          modifier = Modifier.padding(horizontal = 20.dp)
        )
        combinedTextField(
          label = "Email",
          placeholder = "ahmed@gmail.com",
          modifier = Modifier.padding(horizontal = 20.dp)
        )
        combinedTextField(
          label = "Password",
          placeholder = "**************",
          modifier = Modifier.padding(horizontal = 20.dp)
        )
        combinedTextField(
          label = "Confirm Password",
          placeholder = "**************",
          modifier = Modifier.padding(horizontal = 20.dp)
        )


      }
    }

    composeButton(
      "Register",
      "\"Already have an account ? \"",
      "Sign in",
      modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth()
    )


  }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun combinedTextField(label: String, placeholder: String, modifier: Modifier = Modifier) {
  var text by remember { mutableStateOf(TextFieldValue("")) }
  OutlinedTextField(
    value = text,
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(10.dp),
    onValueChange = {
      text = it
    },
    label = {
      Text(
        text = label, style = TextStyle(
          fontFamily = fontsfamilys.poppinsFamily,
          fontWeight = FontWeight(200),
          color = colorResource(id = R.color.labelColor),
          textAlign = TextAlign.Center,
        )
      )
    },
    placeholder = {
      Text(
        text = placeholder, style = TextStyle(
          fontFamily = fontsfamilys.poppinsFamily,
          fontWeight = FontWeight(200),
          color = colorResource(id = R.color.placeholderColor),
          textAlign = TextAlign.Center,
        )
      )
    },

    )

}

@Composable
fun headline(title: String, par: String? = null, modifier: Modifier = Modifier) {
  Column(
    modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(5.dp)
  ) {


    Text(
      text = title,
      style = TextStyle(
        fontSize = 22.sp,
        fontFamily = fontsfamilys.poppinsFamily,
        fontWeight = FontWeight(700),
        color = Color(0xE5000000),

        textAlign = TextAlign.Center,
      )
    )
    if (par != null) {
      Text(
        text = par,
        style = TextStyle(
          fontSize = 15.sp,
          fontFamily = fontsfamilys.poppinsFamily,
          fontWeight = FontWeight(200),
          color = Color(0xE5000000),
          textAlign = TextAlign.Center,
        )
      )
    }
  }
}

@Composable
fun composeButton(
  textButton: String,
  instructionText: String?=null,
  navText: String?=null,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Button(modifier = Modifier
,      colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.buttonColor)),
      shape = RoundedCornerShape(5.dp),
      onClick = { /*TODO*/ }) {
      Text(
        text = textButton,
        style = TextStyle(
          fontSize = 15.sp,
          fontFamily = fontsfamilys.poppinsFamily,
          fontWeight = FontWeight(700),
          color = Color(0xE5000000),
          textAlign = TextAlign.Center,
        )
      )
    }
    if (instructionText!=null){
    Text(
      text = buildAnnotatedString {
        append(instructionText)
        pushStyle(
          SpanStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight(600),
            color = colorResource(id = R.color.buttonColor)
          )
        )
        append(navText)
      },
      style = TextStyle(
        fontSize = 12.sp,
        fontFamily = fontsfamilys.poppinsFamily,
        fontWeight = FontWeight(600),
        color = Color(0xCC000000),

        )
    )
  }}
}
