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
import ru.den.omg.data.entity.Saturday_Entity
import ru.den.omg.navigations.bottomNavigation.BottomAppBar
import ru.den.omg.ui.theme.OmgTheme
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
                Row {
                    TextField(value = saturdayViewModel.newText,
                        onValueChange = {string ->
                            saturdayViewModel.newText = string
                        }, label = { Text(text = "Введите урок") },
                        modifier = Modifier
                            .padding(start = 5.dp, top = 5.dp))

                    IconButton(
                        onClick = {
                            if (saturdayViewModel.newText != "") saturdayViewModel.insertItem()
                        },
                        modifier = Modifier
                            .padding(10.dp)) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
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