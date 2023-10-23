package ru.den.omg.screens.week

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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.den.omg.data.entity.Saturday_Entity
import ru.den.omg.navigations.bottomNavigation.BottomAppBar
import ru.den.omg.ui.theme.OmgTheme
import ru.den.omg.viewModels.MainViewModel
import ru.den.omg.viewModels.SaturdayViewModel

@Composable
fun Saturday_Week(navController: NavController) {
    val saturdayViewModel: SaturdayViewModel = viewModel(factory = SaturdayViewModel.factory)
    val list = saturdayViewModel.itemList.collectAsState(initial = emptyList())
    OmgTheme {
        Scaffold(
            bottomBar = { BottomAppBar(navController = navController) }
        ) {
            Column {
                Text("Суббота", fontSize = 30.sp, modifier = Modifier.padding(10.dp))
                Column {
                    OutlinedTextField(value = saturdayViewModel.newText,
                        onValueChange = { item ->
                            saturdayViewModel.newText = item
                        }, label = { Text(text = "Введите урок") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, top = 5.dp, end = 5.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    if (saturdayViewModel.newText != "") saturdayViewModel.insertItem()
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
                        isError = MainViewModel.isNumeric(saturdayViewModel.newText)
                    )
                    Row {
                        TextField(value = saturdayViewModel.newTimeBefore,
                            onValueChange = { saturdayViewModel.newTimeBefore = it },
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
                        TextField(value = saturdayViewModel.newTimeAfter,
                            onValueChange = { saturdayViewModel.newTimeAfter = it },
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
                        ListItem(item) {
                            saturdayViewModel.deleteItem(item)
                        }
                    }
                }
            }
            it
        }
    }
}

@Composable
fun ListItem(item: Saturday_Entity, onDelete: (Saturday_Entity) -> Unit) {
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
            IconButton(onClick = { onDelete(item) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete2")
            }
        }
    }
}