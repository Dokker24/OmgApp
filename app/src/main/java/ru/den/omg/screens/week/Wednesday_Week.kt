package ru.den.omg.screens.week

import android.content.Context
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.den.omg.R
import ru.den.omg.data.entity.Monday_Entity
import ru.den.omg.data.entity.Wednesday_Entity
import ru.den.omg.navigations.bottomNavigation.BottomAppBar
import ru.den.omg.ui.theme.OmgTheme
import ru.den.omg.viewModels.MainViewModel
import ru.den.omg.viewModels.TuesdayViewModel
import ru.den.omg.viewModels.WednesdayViewModel

@Composable
fun Wednesday_Week(
    navController: NavController,
    context: Context,
    wedViewModel: WednesdayViewModel = hiltViewModel()
    ) {

    val list = wedViewModel.itemList.collectAsState(initial = emptyList())
    OmgTheme {
        Scaffold(
            bottomBar = { BottomAppBar(navController = navController) },
            containerColor = Color(0xFF9CE59E)
        ) {
            TopBarWeek(navController = navController, title = stringResource(id = R.string.wednesday))
            Column(modifier = Modifier.padding(top = 60.dp)) {
                Column {
                    OutlinedTextField(value = wedViewModel.newText,
                        onValueChange = { item ->
                            wedViewModel.newText = item
                        }, label = { Text(text = "Введите урок") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, top = 5.dp, end = 5.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    if (wedViewModel.newText != "") wedViewModel.insertItem()
                                },
                                modifier = Modifier
                                    .padding(10.dp)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Add")
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor = Color(0xFF6200EE),
                            disabledIndicatorColor = Color(0xFF6200EE),
                            focusedIndicatorColor = Color(0xFF6200EE),
                            focusedContainerColor = Color.Transparent.copy(alpha = 0.7f),
                            unfocusedContainerColor = Color.Transparent.copy(alpha = 0.7f),




                            errorSuffixColor = Color.Red,
                            errorCursorColor = Color.Red,
                            errorIndicatorColor = Color.Red,
                            errorLabelColor = Color.Red,
                            errorLeadingIconColor = Color.Red,
                            errorTrailingIconColor = Color.Red
                        ),
                        isError = MainViewModel.isNumeric(wedViewModel.newText)
                    )
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF6200EE)
                        ),
                        modifier = Modifier
                            .padding(5.dp)
                            .size(200.dp, 50.dp)
                    ) {
                        Row {
                            TextField(value = wedViewModel.newTimeBefore,
                                onValueChange = { wedViewModel.newTimeBefore = it },
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent
                                ),
                                modifier = Modifier.size(90.dp, 60.dp),
                                singleLine = true)
                            Text(" - ", fontSize = 20.sp, modifier = Modifier.padding(start = 0.dp, 20.dp))
                            TextField(value = wedViewModel.newTimeAfter,
                                onValueChange = { wedViewModel.newTimeAfter = it },
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledLabelColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent
                                ),
                                modifier = Modifier.size(120.dp, 60.dp),
                                singleLine = true)
                        }
                    }

                }
                LazyColumn {
                    items(list.value) { item ->
                        ListItem(item, {
                            wedViewModel.deleteItem(item)
                        }, {
                            wedViewModel.sendNotify(context, item)
                        }, { wed ->
                            wedViewModel.wed = wed
                            wedViewModel.newText = wed.lesson
                        })
                    }
                }
            }
            it
        }
    }
}

@Composable
fun ListItem(item: Wednesday_Entity,
             onDelete: (Wednesday_Entity) -> Unit,
             sendNotify: (Wednesday_Entity) -> Unit,
             redact: (Wednesday_Entity) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF6200EE)
        )) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(text = item.lesson, fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp, 5.dp), color = Color.White)
                Text(text = item.time, fontSize = 18.sp, modifier = Modifier.padding(5.dp), color = Color.White)
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