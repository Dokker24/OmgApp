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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.den.omg.R
import ru.den.omg.data.entity.Thursday_Entity
import ru.den.omg.data.entity.Wednesday_Entity
import ru.den.omg.navigations.bottomNavigation.BottomAppBar
import ru.den.omg.ui.theme.OmgTheme
import ru.den.omg.viewModels.MainViewModel
import ru.den.omg.viewModels.ThursdayViewModel
import ru.den.omg.viewModels.WednesdayViewModel

@Composable
fun Thursday_Week(navController: NavController, context: Context) {
    val thuViewModel: ThursdayViewModel = viewModel(factory = ThursdayViewModel.factory)
    val list = thuViewModel.itemList.collectAsState(initial = emptyList())
    OmgTheme {
        Scaffold(
            bottomBar = { BottomAppBar(navController = navController) }
        ) {
            Column {
                Text("Четверг", fontSize = 30.sp, modifier = Modifier.padding(10.dp))
                Column {
                    OutlinedTextField(value = thuViewModel.newText,
                        onValueChange = { item ->
                            thuViewModel.newText = item
                        }, label = { Text(text = "Введите урок") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, top = 5.dp, end = 5.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    if (thuViewModel.newText != "") thuViewModel.insertItem()
                                },
                                modifier = Modifier
                                    .padding(10.dp)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Add")
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            errorSuffixColor = Color.Red,
                            errorCursorColor = Color.Red,
                            errorIndicatorColor = Color.Red,
                            errorLabelColor = Color.Red,
                            errorLeadingIconColor = Color.Red,
                            errorTrailingIconColor = Color.Red
                        ),
                        isError = MainViewModel.isNumeric(thuViewModel.newText)
                    )
                    Row {
                        TextField(value = thuViewModel.newTimeBefore,
                            onValueChange = { thuViewModel.newTimeBefore = it },
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
                        TextField(value = thuViewModel.newTimeAfter,
                            onValueChange = { thuViewModel.newTimeAfter = it },
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
                LazyColumn {
                    items(list.value) { item ->
                        ListItem(item, {
                            thuViewModel.deleteItem(item)
                        }, {
                           thuViewModel.sendNotify(context, item)
                        }, { thu ->
                            thuViewModel.thursday = thu
                            thuViewModel.newText = thu.lesson
                        })
                    }
                }
            }
            it
        }
    }
}

@Composable
fun ListItem(item: Thursday_Entity,
             onDelete: (Thursday_Entity) -> Unit,
             sendNotify: (Thursday_Entity) -> Unit,
             redact: (Thursday_Entity) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF6200EE)
        )) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(text = item.lesson, fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp, 5.dp))
                Text(text = item.time, fontSize = 18.sp, modifier = Modifier.padding(5.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                expanded = true
            }) {
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