package ru.den.omg.screens.week


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.den.omg.data.entity.Tuesday_Entity
import ru.den.omg.navigations.bottomNavigation.BottomAppBar
import ru.den.omg.ui.theme.OmgTheme
import ru.den.omg.viewModels.TuesdayViewModel

@Composable
fun Tuesday_Week(navController: NavController) {
    val tuesdayViewModel: TuesdayViewModel = viewModel(factory = TuesdayViewModel.factory)
    val list = tuesdayViewModel.itemList.collectAsState(initial = emptyList())
    OmgTheme {
        Scaffold(
            bottomBar = { BottomAppBar(navController = navController) }
        ) {
            Column {
                Row {
                    TextField(value = tuesdayViewModel.newText,
                        onValueChange = {string ->
                                        tuesdayViewModel.newText = string
                        }, label = { Text(text = "Введите урок") },
                        modifier = Modifier
                            .padding(start = 5.dp, top = 5.dp))

                    IconButton(
                        onClick = {
                                  if (tuesdayViewModel.newText != "") tuesdayViewModel.insertItem()
                        },
                        modifier = Modifier
                            .padding(10.dp)) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
                LazyColumn {
                    items(list.value) { item ->
                        ListItem(item) {
                            tuesdayViewModel.deleteItem(item)
                        }
                    }
                }
            }
            it
        }
    }
}

@Composable
fun ListItem(item: Tuesday_Entity, onDelete: (Tuesday_Entity) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(item.lesson, fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp))
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { onDelete(item) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete2")
            }
        }
    }
}