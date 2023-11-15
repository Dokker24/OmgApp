@file:OptIn(ExperimentalFoundationApi::class)

package ru.den.omg.screens.home




import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.den.omg.R
import ru.den.omg.converter.Top
import ru.den.omg.navigations.Screens
import ru.den.omg.navigations.tabNavigation.Tab
import ru.den.omg.navigations.tabNavigation.TabContent
import ru.den.omg.navigations.tabNavigation.TabItems


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavController
    ) {
    val tabs = listOf(TabItems.ListWeek, TabItems.ListMonth)
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabs.size })
        Surface {
            Scaffold(
                bottomBar = { ru.den.omg.navigations.bottomNavigation.BottomAppBar(
                navController = navController,
            ) },
                containerColor = Color(0xFF9CE59E)
            ) {
                Column {
                    Top(stringResource(id = R.string.home))
                    Tab(tabs = tabs, pagerState)
                    TabContent(tabs = tabs, pagerState = pagerState, navController = navController)
                }
                it
            }
        }
    }


//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun Home(navController: NavController) {
//    val FONT_SIZE = 20
//    var state = rememberLazyListState()
//    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState = state)
//    val view: HomeViewModel = viewModel(factory = HomeViewModel.factory)
//    val listMonday = view.monday.collectAsState(initial = emptyList()).value
//    val listTuesday = view.tuesday.collectAsState(initial = emptyList()).value
//    val listWednesday = view.wednesday.collectAsState(initial = emptyList()).value
//    val listThursday = view.thursday.collectAsState(initial = emptyList()).value
//    val listFriday = view.friday.collectAsState(initial = emptyList()).value
//    val listSaturday = view.saturday.collectAsState(initial = emptyList()).value
//
//
//    LazyColumn(
//        state = state,
//        flingBehavior = snapFlingBehavior,
//        modifier = Modifier.padding(top = 60.dp)
//    ) {
//        item {
//            OutlinedCard (
//                colors = CardDefaults.cardColors(
//                    containerColor = Color(0xFF6200EE)
//                ),
//                modifier = Modifier
//                    .size(width = 350.dp, height = 470.dp)
//                    .padding(10.dp)
//                    .clickable {
//                        navController.navigate(Screens.Week.route)
//                    },
//                elevation = CardDefaults.cardElevation(15.dp)
//            ) {
//                Textap("Понедельник")
//                LazyColumn{
//                    items(listMonday) {
//                        Textup(it.lesson, it.time, font = FONT_SIZE)
//                    }
//                }
//            }
//        }
//        item {
//            OutlinedCard(
//                colors = CardDefaults.cardColors(
//                    containerColor = Color(0xFF6200EE)
//                ),
//                modifier = Modifier
//                    .size(width = 350.dp, height = 470.dp)
//                    .padding(10.dp)
//                    .clickable { navController.navigate(Screens.Tuesday.route) },
//                elevation = CardDefaults.cardElevation(15.dp)
//            ) {
//                Textap("Вторник")
//                LazyColumn{
//                    items(listTuesday) {
//                        Textup(it.lesson, it.time, font = FONT_SIZE)
//                    }
//                }
//            }
//        }
//        item {
//            OutlinedCard(
//                colors = CardDefaults.cardColors(
//                    containerColor = Color(0xFF6200EE)
//                ),
//                modifier = Modifier
//                    .size(width = 350.dp, height = 470.dp)
//                    .padding(10.dp)
//                    .clickable { navController.navigate(Screens.Wednesday.route) },
//                elevation = CardDefaults.cardElevation(15.dp)
//            ) {
//                Textap(text = "Среда")
//                LazyColumn{
//                    items(listWednesday) {
//                        Textup(it.lesson, it.time, font = FONT_SIZE)
//                    }
//                }
//            }
//        }
//        item {
//            OutlinedCard(
//                colors = CardDefaults.cardColors(
//                    containerColor = Color(0xFF6200EE)
//                ),
//                modifier = Modifier
//                    .size(width = 350.dp, height = 470.dp)
//                    .padding(10.dp)
//                    .clickable { navController.navigate(Screens.Thursday.route) },
//                elevation = CardDefaults.cardElevation(15.dp)
//            ) {
//                Textap(text = "Четверг")
//                LazyColumn{
//                    items(listThursday) {
//                        Textup(it.lesson, it.time, font = FONT_SIZE)
//                    }
//                }
//            }
//        }
//        item {
//            OutlinedCard(
//                colors = CardDefaults.cardColors(
//                    containerColor = Color(0xFF6200EE)
//                ),
//                modifier = Modifier
//                    .size(width = 350.dp, height = 470.dp)
//                    .padding(10.dp)
//                    .clickable { navController.navigate(Screens.Friday.route) },
//                elevation = CardDefaults.cardElevation(15.dp)
//            ) {
//                Textap(text = "Пятница")
//                LazyColumn{
//                    items(listFriday) {
//                        Textup(it.lesson,it.time, font = FONT_SIZE)
//                    }
//                }
//            }
//        }
//        item {
//            OutlinedCard(colors = CardDefaults.cardColors(
//                containerColor = Color(0xFF6200EE)
//            ),
//                modifier = Modifier
//                    .size(width = 350.dp, height = 470.dp)
//                    .padding(10.dp, bottom = 90.dp)
//                    .clickable { navController.navigate(Screens.Saturday.route) },
//                elevation = CardDefaults.cardElevation(15.dp)
//            ) {
//                Textap(text = "Суббота")
//                LazyColumn{
//                    items(listSaturday) {
//                        Textup(it.lesson,it.time, font = FONT_SIZE)
//                    }
//                }
//            }
//        }
//
//        item {
//            Text(text = "Ден всегда рядом...")
//        }
//    }
//}
//
//@Composable
//fun Textap(text: String) {
//    Text(text = text, fontSize = 30.sp, modifier = Modifier.padding(10.dp))
//}
//
//@Composable
//fun Textup(textLesson: String, time: String,  font: Int) {
//    Text(text = "$textLesson\n$time", fontSize = font.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
//    Divider()
//}

