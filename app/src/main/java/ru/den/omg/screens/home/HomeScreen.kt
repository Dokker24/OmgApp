package ru.den.omg.screens.home




import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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
                    Text(text = "Главная"
                        , fontSize = 35.sp
                        , modifier = Modifier
                        .padding(start = 15.dp, top = 5.dp, bottom = 20.dp))
                    Home()
                }
                it
            }
        }
    }
}

@Composable
fun Home() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable {

                }
        ) {
            Column {
                Text(text = "Понедельник", fontSize = 25.sp, modifier = Modifier.padding(start = 15.dp))
                LazyColumn {
                    items(5) {
                        Text(text = "", fontSize = 20.sp,
                            modifier = Modifier
                                .padding(start = 15.dp))
                        Text(text= "8:00 - 8:40")
                    }
                }
            }
        }
}