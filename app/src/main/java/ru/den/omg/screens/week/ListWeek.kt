package ru.den.omg.screens.week

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.den.omg.data.entity.Week_Entity
import ru.den.omg.screens.home.HomeViewModel

@Composable
fun ListWeek() {
    Home()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(
    view: HomeViewModel = hiltViewModel()
) {
    val state = rememberLazyListState()
    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState = state)
    val listMonday = view.monday.collectAsState(initial = emptyList()).value
    val listTuesday = view.tuesday.collectAsState(initial = emptyList()).value
    val listWednesday = view.wednesday.collectAsState(initial = emptyList()).value
    val listThursday = view.thursday.collectAsState(initial = emptyList()).value
    val listFriday = view.friday.collectAsState(initial = emptyList()).value
    val listSaturday = view.saturday.collectAsState(initial = emptyList()).value

    val listTitle = listOf(
        "Понедельник",
        "Вторник",
        "Среда",
        "Четверг",
        "Пятница",
        "Суббота"
    )

    val listDayOfWeek = listOf(
        listMonday,
        listTuesday,
        listWednesday,
        listThursday,
        listFriday,
        listSaturday
        )


    LazyColumn(
        state = state,
        flingBehavior = snapFlingBehavior,
        modifier = Modifier.padding(bottom = 60.dp)
    ) {

        items(listDayOfWeek zip listTitle) {
            ItemLazyColumn(list = it.first, title = it.second, fontSize = 20)
        }
    }
}

@Composable
fun TextTop(text: String) {
    Text(text = text, fontSize = 30.sp, modifier = Modifier.padding(10.dp), color = Color.White)
}

@Composable
fun TextDis(textLesson: String, time: String,  font: Int) {
    Text(text = "$textLesson\n$time", fontSize = font.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp), color = Color.White)
    Divider()
}

@Composable
fun ItemLazyColumn(list: List<Week_Entity>, title: String, fontSize: Int) {
        OutlinedCard (
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF6200EE)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(10.dp)
                .clickable {
//                        navController.navigate(Screens.Week.route)
                },
            elevation = CardDefaults.cardElevation(15.dp)
        ) {
            TextTop(title)
            LazyColumn{
                items(list) {
                    TextDis(it.lesson, it.time, font = fontSize)
                }
            }
        }

}