package com.example.todoapp.ui.screens.homescreen

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.models.NoteModel
import com.example.todoapp.R
import com.example.todoapp.staticImage
import com.example.todoapp.ui.components.BoldTextField
import com.example.todoapp.ui.components.ButtonComponent
import com.example.todoapp.ui.components.MyTextField
import com.example.todoapp.utils.fontsfamilys
import com.example.todoapp.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
  navController: NavHostController,
  userName: String?,
  homeViewModel: HomeViewModel = hiltViewModel()
) {
  val state = homeViewModel.state

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
      RoundedImage(painterResource(id = R.drawable.gayar), userName, modifier = Modifier.padding())
    }
    MiddleImage(Modifier.size(100.dp))
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 20.dp, start = 10.dp, end = 10.dp),
      verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
      NotesSection()
      NotesList(
        onTitleChanged = { homeViewModel.onEvent(HomeScreenUiEvent.noteTitleChanged(it)) },
        onSaveClick = { homeViewModel.addNote() },
        notes = state.notes
      )

    }
  }


}

@Composable
fun NotesSection(modifier: Modifier = Modifier) {


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


}

@Composable
fun NotesList(
  onTitleChanged: (String) -> Unit,
  onSaveClick: () -> Unit,
  notes: List<NoteModel>
) {
  var isNotesVisible by remember {
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
    AnimatedVisibility(isNotesVisible) {
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        MyTextField(
          label = stringResource(R.string.title),
          placeholder = stringResource(R.string.note_title),
          modifier = Modifier.padding(horizontal = 10.dp),
          onValueChanged = {
            onTitleChanged(it)
          }
        )
        MyTextField(
          label = stringResource(R.string.description),
          placeholder = stringResource(R.string.note_description),
          modifier = Modifier.padding(horizontal = 10.dp),
          onValueChanged = {

          }
        )

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          ButtonComponent(
            stringResource(R.string.save),
            modifier = Modifier.padding(10.dp),
            onClick = {
              onSaveClick()
              isNotesVisible = !isNotesVisible
            })

        }
      }

    }
    AnimatedVisibility(visible = !isNotesVisible) {


      Column {


        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = stringResource(R.string.daily_tasks),
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
                isNotesVisible = !isNotesVisible
              }
          )
        }

        LazyColumn(
          modifier = Modifier, verticalArrangement = Arrangement.spacedBy(1.dp),
        ) {
          items(notes) {
            CustomizedCheckBox(it.title)
          }
        }

      }
    }
  }
}

@Composable
fun MiddleImage(modifier: Modifier = Modifier) {
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
    BoldTextField(value = "Welcome $text!", size = 20.sp)
  }
}


@Composable
fun CustomizedCheckBox(text: String, modifier: Modifier = Modifier) {
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
      text = text,
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

