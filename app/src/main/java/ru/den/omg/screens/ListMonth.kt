package ru.den.omg.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.den.omg.data.entity.Calendar_Entity
import ru.den.omg.viewModels.CalendarViewModel

@Composable
fun ListMonth(
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val list = viewModel.listItems.collectAsState(initial = emptyList())
    LazyColumn {
        items(list.value) {
            CaseCard(it, viewModel)
        }
    }
}

@Composable
fun CaseCard(
    item: Calendar_Entity,
    viewModel: CalendarViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF6200EE)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp)) {
                    Column(modifier = Modifier
                        .padding(start = 5.dp)
                    ) {
                        androidx.compose.material3.Text(text = item.data, fontSize = 23.sp)
                        Text(text = item.party, fontSize = 20.sp, color = Color.White)
                    }
                    Spacer(modifier = Modifier.weight(1f, true))
                    IconButton(onClick = {
                        expanded = true
                    }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "о чём")
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    offset = DpOffset(x = 220.dp, y = 0.dp)
                ) {
//                    DropdownMenuItem(onClick = {
                        /*******************
                         * Скоро появится...
                         *******************/
//                        viewModel.sendNotify()
//                    }) {
//                        Icon(Icons.Default.Notifications, contentDescription = "Notificate_Month")
//                        Text("Уведомить", color = Color.White)
//                    }
                    DropdownMenuItem(onClick = { viewModel.deleteItem(item); expanded = false }) {
                        Icon(Icons.Default.Delete, contentDescription = "Del", tint = Color.Red)
                        Text("Удалить", color = Color.Red)

                    }
                }
            }
        }
    }
}