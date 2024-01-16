package com.example.todoapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.utils.fontsfamilys

@Composable
fun NormalTextField(value: String, modifier: Modifier = Modifier) {
  Text(
    text = value,
    modifier = modifier
      .fillMaxWidth()
      .heightIn(min = 70.dp),
    fontFamily = fontsfamilys.poppinsFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight(200),
    color = colorResource(id = R.color.black),
    textAlign = TextAlign.Center
  )
}

@Composable
fun BoldTextField(value: String, modifier: Modifier = Modifier) {
  Text(
    text = value,
    modifier = modifier
      .fillMaxWidth()
      .heightIn(),
    fontFamily = fontsfamilys.poppinsFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.Bold,
    color = colorResource(id = R.color.black),
    textAlign = TextAlign.Center
  )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(label: String, placeholder: String,onValueChanged : (String) -> Unit , modifier: Modifier = Modifier) {
  var text by remember { mutableStateOf(TextFieldValue("")) }
  OutlinedTextField(
    value = text,
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(10.dp),
    onValueChange = {
      text = it
      onValueChanged(it.text)
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
    keyboardOptions = KeyboardOptions.Default

  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(label: String, placeholder: String,onValueChanged:(String)-> Unit, modifier: Modifier = Modifier) {
  var text by remember { mutableStateOf(TextFieldValue("")) }
  var passwordVisibile by remember { mutableStateOf(false) }
  OutlinedTextField(
    value = text,
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(10.dp),
    onValueChange = {
      text = it
      onValueChanged(it.text)
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
    keyboardOptions = KeyboardOptions(keyboardType = if (passwordVisibile) KeyboardType.Password else KeyboardType.Text),
    trailingIcon = {
      if (passwordVisibile)
        Icon(
          modifier = modifier.clickable {
            passwordVisibile = false
          }, imageVector = Icons.Filled.Visibility, contentDescription = "Password Shown"
        )
      else
        Icon(
          modifier = modifier
            .widthIn()
            .heightIn()
            .clickable {
              passwordVisibile = true
            }, imageVector = Icons.Filled.VisibilityOff, contentDescription = "Hide Password"
        )
    },
    visualTransformation = if (passwordVisibile) VisualTransformation.None else PasswordVisualTransformation()

  )
}


@Composable
fun ButtonComponent(value: String, onClick: () -> (Unit) , modifier: Modifier=Modifier) {
  Button(modifier = modifier
    .fillMaxWidth()
    .heightIn(min = 48.dp),
    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.mainColor)),
    shape = RoundedCornerShape(5.dp),
    onClick = { onClick() }) {
    BoldTextField(value = value)

  }
}

@Composable
fun TextClickable(instructionText: String, clickableText: String, onClick: () -> (Unit), modifier: Modifier = Modifier
) {
  val annotatedText = buildAnnotatedString {
    pushStyle(
      SpanStyle(
        fontSize = 12.sp,
        fontFamily = fontsfamilys.poppinsFamily,
        fontWeight = FontWeight(600),
        color = Color(0xCC000000),
      )
    )
    append(instructionText)
    pop()
    pushStringAnnotation(
      tag = clickableText,// provide tag which will then be provided when you click the text
      annotation = clickableText
    )
    pushStyle(
      SpanStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight(600),
        color = colorResource(id = R.color.mainColor)
      )
    )

    append(clickableText)
    pop()

  }


  ClickableText(modifier = modifier, text = annotatedText) { offset ->
    annotatedText.getStringAnnotations(
      tag = clickableText,// tag which you used in the buildAnnotatedString
      start = offset,
      end = offset
    ).forEach() { annotation ->
      //do your stuff when it gets clicked
      onClick()
    }
  }

}


@Composable
fun NormalButton(onClick: () -> Unit, modifier: Modifier =Modifier){
  Button(modifier = modifier
    .fillMaxWidth()
    .heightIn(min = 48.dp),
    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.mainColor)),
    shape = RoundedCornerShape(5.dp),
    onClick = { onClick() }) {
    BoldTextField(value = stringResource(R.string.get_started))
  }
}
