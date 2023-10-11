package ru.den.omg.screens.home




import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.den.omg.ui.theme.OmgTheme

@Composable
fun HomeScreen(
    navController: NavController
    ) {
    OmgTheme {
        Surface {
            Scaffold(bottomBar = { ru.den.omg.navigations.bottomNavigation.BottomAppBar(
                navController = navController
            ) }) {
                Column {
                    Text(text = "Главная",
                        fontSize = 35.sp,
                        modifier = Modifier
                        .padding(start = 15.dp, top = 5.dp, bottom = 20.dp))
                    Home()
                }
                it
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home() {
    val FONT_SIZE = 20
    var state = rememberLazyListState()
    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState = state)
    val view: HomeViewModel = viewModel(factory = HomeViewModel.factory)
    val listMonday = view.monday.collectAsState(initial = emptyList()).value
    val listTuesday = view.tuesday.collectAsState(initial = emptyList()).value
    val listWednesday = view.wednesday.collectAsState(initial = emptyList()).value
    val listThursday = view.thursday.collectAsState(initial = emptyList()).value
    val listFriday = view.friday.collectAsState(initial = emptyList()).value
    val listSaturday = view.saturday.collectAsState(initial = emptyList()).value

    LazyRow(
        state = state,
        flingBehavior = snapFlingBehavior
    ) {
        item {
            Card(
                modifier = Modifier
                    .size(width = 350.dp, height = 400.dp)
                    .padding(10.dp)
            ) {
                Textap("Понедельник")
                LazyColumn{
                    items(listMonday) {
                        Textup(text = it.lesson, font = FONT_SIZE)
                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 350.dp, height = 400.dp)
                    .padding(10.dp)
            ) {
                Textap("Вторник")
                LazyColumn{
                    items(listTuesday) {
                        Textup(text = it.lesson, font = FONT_SIZE)
                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 350.dp, height = 400.dp)
                    .padding(10.dp)
            ) {
                Textap(text = "Среда")
                LazyColumn{
                    items(listWednesday) {
                        Textup(text = it.lesson, font = FONT_SIZE)
                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 350.dp, height = 400.dp)
                    .padding(10.dp)
            ) {
                Textap(text = "Четверг")
                LazyColumn{
                    items(listThursday) {
                        Textup(text = it.lesson, font = FONT_SIZE)
                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 350.dp, height = 400.dp)
                    .padding(10.dp)
            ) {
                Textap(text = "Пятница")
                LazyColumn{
                    items(listFriday) {
                        Textup(text = it.lesson, font = FONT_SIZE)
                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 350.dp, height = 400.dp)
                    .padding(10.dp)
            ) {
                Textap(text = "Суббота")
                LazyColumn{
                    items(listSaturday) {
                        Textup(text = it.lesson, font = FONT_SIZE)
                    }
                }
            }
        }
    }
}

@Composable
fun Textap(text: String) {
    Text(text = text, fontSize = 30.sp, modifier = Modifier.padding(10.dp))
}

@Composable
fun Textup(text: String, font: Int) {
    Text(text = text, fontSize = font.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
    Divider()
}