

package ru.den.omg.screens.calendar


import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ru.den.omg.R
import ru.den.omg.converter.Top
import ru.den.omg.data.entity.Calendar_Entity
import ru.den.omg.navigations.bottomNavigation.BottomAppBar
import ru.den.omg.ui.theme.OmgTheme
import ru.den.omg.viewModels.CalendarViewModel
import ru.den.omg.viewModels.MainViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(navController: NavHostController) {
    val mondayViewModel: CalendarViewModel = viewModel(factory = CalendarViewModel.factory)
    val list = mondayViewModel.listItems.collectAsState(initial = emptyList())
    OmgTheme {
        Scaffold(
            bottomBar = { BottomAppBar(navController = navController) },
            topBar = {
                Top(title = stringResource(id = R.string.calendar))
            },
            containerColor = Color(0xFF9CE59E)
        ) {
            val calendar = Calendar.getInstance()
            calendar.set(2009, 3, 14)  // add year, month (Jan), date

            // set the initial date
            val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)

            Column {
                DatePicker(
                    state = datePickerState,
                    colors = DatePickerDefaults.colors(
                        titleContentColor = Color(0xFF6200EE)
                    ),
                    modifier = Modifier
                        .size(348.dp, 580.dp)
                        .padding(5.dp, top = it.calculateTopPadding() + 10.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .background(Color(0xFF6200EE))
                )

                OutlinedTextField(value = mondayViewModel.party,
                    onValueChange = { item ->
                        mondayViewModel.party = item
                    }, label = { Text(text = "Какое в этот день событие?") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, end = 5.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color(0xFF6200EE),
                        disabledIndicatorColor = Color(0xFF6200EE),
                        focusedIndicatorColor = Color(0xFF6200EE),
                        focusedContainerColor = Color.Transparent.copy(alpha = 0.7f),
                        unfocusedContainerColor = Color.Transparent.copy(alpha = 0.7f),



                        // если ошибка
                        errorSuffixColor = Color.Red,
                        errorCursorColor = Color.Red,
                        errorIndicatorColor = Color.Red,
                        errorLabelColor = Color.Red,
                        errorLeadingIconColor = Color.Red,
                        errorTrailingIconColor = Color.Red
                    ),
                    isError = MainViewModel.isNumeric(mondayViewModel.party),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                if (mondayViewModel.party != "") mondayViewModel.insertItem()
                            },
                            modifier = Modifier
                                .padding(10.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                )

                LazyColumn {
                    items(list.value) { item ->
                        ListItem(item = item,
                            onDelete = { mondayViewModel.deleteItem(item) },
                            sendNotify = {

                            },
                            redact = { calendar ->
                                mondayViewModel.calendar = calendar
                                mondayViewModel.party = calendar.party
                            }
                        )
                    }
                }

                Text("Ден", modifier = Modifier.padding(top = 700.dp))
            }
        }
    }
}

@Composable
fun ListItem(item: Calendar_Entity,
             onDelete: (Calendar_Entity) -> Unit,
             sendNotify: (Calendar_Entity) -> Unit,
             redact: (Calendar_Entity) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF6200EE)
        )) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(text = item.party, fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp, 5.dp), color = Color.White)
                Text(text = item.data, fontSize = 18.sp, modifier = Modifier.padding(5.dp), color = Color.White)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                expanded = true
            },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White
                )) {
                Icon(Icons.Default.MoreVert, contentDescription = "More")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = DpOffset(x = 220.dp, y = 0.dp)
            ) {
                DropdownMenuItem(text = {
                    Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom) {
                        Icon(Icons.Default.Notifications, contentDescription = "sendNotify", modifier = Modifier.padding(end = 5.dp))
                        Text(text = stringResource(id = R.string.sendNotify))
                    }
                }, onClick = {
                    sendNotify(item)
                    expanded = false
                } )
                Divider()
                DropdownMenuItem(
                    text = {
                        Row(horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.Bottom) {
                            Icon(Icons.Default.Create, contentDescription = "Redacting", modifier = Modifier.padding(end = 5.dp))
                            Text(text = stringResource(id = R.string.redacting))
                        }
                    },
                    onClick = {
                        redact(item)
                        expanded = false
                    })
                Divider()
                DropdownMenuItem(text = {
                    Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete3", tint = Color.Red, modifier = Modifier.padding(end = 5.dp))
                        Text(text = stringResource(id = R.string.delete), color = Color.Red)
                    }
                },
                    onClick = {
                        onDelete(item)
                        expanded = false
                    })
            }
        }
    }
}