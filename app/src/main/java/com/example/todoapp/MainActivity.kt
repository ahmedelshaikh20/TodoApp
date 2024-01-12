package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.ui.screens.HomeScreen
import com.example.todoapp.ui.screens.SignInScreen
import com.example.todoapp.ui.screens.SignUpScreen
import com.example.todoapp.ui.screens.SplashScreen
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      TodoAppTheme {
        // A surface container using the 'background' color from the theme
        Column(
          modifier = Modifier.fillMaxSize(),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {


          HomeScreen()

        }
      }
    }
  }
}


@Composable
fun staticImage(modifier: Modifier = Modifier, background: Color = Color.White) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(color = background)
  ) {
    Image(
      painter = painterResource(id = R.drawable.shape),
      contentDescription = "Background Image",
      Modifier
        .width(100.dp)
        .height(100.dp)
        .align(Alignment.TopStart),
      contentScale = ContentScale.FillBounds
    )
  }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  TodoAppTheme {
    Greeting("Android")
  }
}
