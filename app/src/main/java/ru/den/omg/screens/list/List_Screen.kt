package ru.den.omg.screens.list

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.den.omg.navigations.Screens
import ru.den.omg.navigations.bottomNavigation.BottomAppBar
import ru.den.omg.ui.theme.OmgTheme

@Composable
fun List(navController: NavController) {
    OmgTheme {
        Surface {
            Scaffold(
                bottomBar = { BottomAppBar(navController = navController) }
            ) {
                Column(modifier = Modifier.verticalScroll(remember { ScrollState(0) })) {
                    Text(text = "Расписание", fontSize = 35.sp, modifier = Modifier.padding(start = 15.dp, top = 5.dp, bottom = 20.dp))
                    CaseCard(name = "Понедельник", navController, Screens.Week.route)
                    CaseCard("Вторник", navController, Screens.Tuesday.route)
                    CaseCard("Среда", navController, Screens.Wednesday.route)
                    CaseCard("Четверг", navController, Screens.Thursday.route)
                    CaseCard("Пятница", navController, Screens.Friday.route)
                    CaseCard("Суббота", navController, Screens.Saturday.route)
                }
                it
            }
        }
    }
}
@Composable
fun  CaseCard(name: String, navController: NavController, navigate: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(5.dp)
            .clickable {
                navController.navigate(navigate)
            },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(10.dp)
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
                        Text(text = name, fontSize = 23.sp)
                    }
                    Spacer(modifier = Modifier.weight(1f, true))
                    IconButton(onClick = {  }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "о чём")
                    }
                }
            }
        }
    }
}