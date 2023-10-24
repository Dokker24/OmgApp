

package ru.den.omg.screens.calendar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.den.omg.navigations.bottomNavigation.BottomAppBar
import ru.den.omg.ui.theme.OmgTheme

@Composable
fun CalendarScreen(navController: NavHostController) {
    OmgTheme {
        Scaffold(
            bottomBar = { BottomAppBar(navController = navController) }
        ) {
            it
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(12) {
                    Card(
                        elevation = CardDefaults.cardElevation(15.dp)
                    ) {

                    }
                }
            }
        }
    }
}