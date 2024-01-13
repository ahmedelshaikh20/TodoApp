package com.example.todoapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.R
import com.example.todoapp.staticImage
import com.example.todoapp.utils.fontsfamilys

@Composable
fun HomeScreen(navController: NavHostController) {

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(colorResource(id = R.color.backgroundColor)),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(20.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .background(colorResource(id = R.color.mainColor))
    ) {
      staticImage(background = colorResource(id = R.color.mainColor))
      RoundedImage(painterResource(id = R.drawable.gayar), "Gayar", modifier = Modifier.padding())
    }
    middleImage(Modifier.size(100.dp))
    notesSection()
  }


}

@Composable
fun notesSection() {

  Column(
    modifier = Modifier.padding(bottom = 20.dp, start = 10.dp, end = 10.dp),
    verticalArrangement = Arrangement.spacedBy(2.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp, start = 10.dp, end = 10.dp),
      horizontalArrangement = Arrangement.Start
    ) {
      Text(
        text = "Tasks List",
        style = TextStyle(
          fontSize = 20.sp,
          fontFamily = fontsfamilys.poppinsFamily,
          fontWeight = FontWeight(700),
          color = Color(0xCC000000),
          textAlign = TextAlign.Start
        )
      )
    }
    notes()

  }


}

@Composable
fun notes() {
  var visible by remember {
    mutableStateOf(false)
  }
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(vertical = 10.dp)
      .shadow(elevation = 4.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
      .background(color = Color.White, shape = RoundedCornerShape(size = 10.dp)),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    if (visible) {
      combinedTextField(
        label = "Title",
        placeholder = "Note Title",
        modifier = Modifier.padding(horizontal = 10.dp)
      )
      combinedTextField(
        label = "Description",
        placeholder = "Note Description",
        modifier = Modifier.padding(horizontal = 10.dp)
      )

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        composeButton(textButton = "Save", modifier = Modifier.padding(10.dp), onClick = {})

      }

    } else {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 10.dp, start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = "Daily Tasks",
          style = TextStyle(
            fontSize = 17.sp,
            fontFamily = fontsfamilys.poppinsFamily,
            fontWeight = FontWeight(700),
            color = Color(0xCC000000),
          )

        )
        Image(
          painter = painterResource(id = R.drawable.pluscircle),
          contentDescription = "image description",
          contentScale = ContentScale.Fit,
          modifier = Modifier
            .size(20.dp)
            .clickable {
              visible = true
            }
        )
      }

      LazyColumn(
        modifier = Modifier, verticalArrangement = Arrangement.spacedBy(1.dp),
      ) {
        items(5) {
          CustomizedCheckBox()
          CustomizedCheckBox()
        }
      }
    }
  }
}

@Composable
fun middleImage(modifier: Modifier = Modifier) {
  Image(
    modifier = modifier,
    painter = painterResource(id = R.drawable.clock),
    contentDescription = "Clock Image"
  )

}


@Composable
fun RoundedImage(
  image: Painter,
  text: String? = null,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(colorResource(id = R.color.mainColor)),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(5.dp)
  ) {


    Image(
      painter = image, contentDescription = "Profile Image",
      modifier = modifier
        .size(100.dp)
        .aspectRatio(1f, matchHeightConstraintsFirst = true)
        .border(
          width = 1.dp,
          color = Color.Gray,
          shape = CircleShape
        )
        .padding(3.dp)
        .clip(CircleShape),
      contentScale = ContentScale.Crop

    )
    Text(
      text = "Welcome $text!",
      style = TextStyle(
        fontSize = 22.sp,
        fontFamily = fontsfamilys.poppinsFamily,
        fontWeight = FontWeight(600),
        color = Color.White,
        textAlign = TextAlign.Center,

        ),
    )
  }
}


@Composable
fun CustomizedCheckBox(modifier: Modifier = Modifier) {
  var checkedState by remember { mutableStateOf(false) }
  Row(
    Modifier
      .fillMaxWidth()
      .toggleable(
        value = checkedState,
        onValueChange = { checkedState = it }
      ),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp)
  ) {

    Checkbox(
      checked = checkedState,
      onCheckedChange = { checkedState = it },
      modifier = modifier,
      colors = CheckboxDefaults.colors(checkedColor = colorResource(id = R.color.mainColor))
    )
    Text(
      text = "Select Option",
      style = TextStyle(
        fontSize = 15.sp,
        fontFamily = fontsfamilys.poppinsFamily,
        fontWeight = FontWeight(700),
        color = Color(0xCC000000),
        textAlign = TextAlign.Center

      )
    )
  }
}

