package ru.den.omg.screens.home




import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.den.omg.navigations.Screens
import ru.den.omg.time.Time
import ru.den.omg.ui.theme.OmgTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
    ) {
        Surface(
        ) {
            Scaffold(
                topBar = {
                         TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                             containerColor = Color.Transparent.copy(alpha = 0.8f)
                         ),
                             title = { Text(text = "Главная", fontSize = 35.sp) }
                         )
                },
                bottomBar = { ru.den.omg.navigations.bottomNavigation.BottomAppBar(
                navController = navController,
            ) },
                containerColor = Color(0xFF000000)
            ) {
                Home(it, navController)
            }
        }
    }


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(top: PaddingValues, navController: NavController) {
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

    LazyColumn(
        state = state,
        flingBehavior = snapFlingBehavior,
        modifier = Modifier.padding(top = top.calculateTopPadding())
    ) {
        item {
            OutlinedCard (
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF6200EE)
                ),
                modifier = Modifier
                    .size(width = 350.dp, height = 470.dp)
                    .padding(10.dp)
                    .clickable {
                        Time.time()
                        navController.navigate(Screens.Week.route)
                    }
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
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF6200EE)
                ),
                modifier = Modifier
                    .size(width = 350.dp, height = 470.dp)
                    .padding(10.dp)
                    .clickable { navController.navigate(Screens.Tuesday.route) }
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
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF6200EE)
                ),
                modifier = Modifier
                    .size(width = 350.dp, height = 470.dp)
                    .padding(10.dp)
                    .clickable { navController.navigate(Screens.Wednesday.route) }
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
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF6200EE)
                ),
                modifier = Modifier
                    .size(width = 350.dp, height = 470.dp)
                    .padding(10.dp)
                    .clickable { navController.navigate(Screens.Thursday.route) }
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
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF6200EE)
                ),
                modifier = Modifier
                    .size(width = 350.dp, height = 470.dp)
                    .padding(10.dp)
                    .clickable { navController.navigate(Screens.Friday.route) }
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
            OutlinedCard(colors = CardDefaults.cardColors(
                containerColor = Color(0xFF6200EE)
            ),
                modifier = Modifier
                    .size(width = 350.dp, height = 470.dp)
                    .padding(10.dp, bottom = 50.dp)
                    .clickable { navController.navigate(Screens.Saturday.route) }
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