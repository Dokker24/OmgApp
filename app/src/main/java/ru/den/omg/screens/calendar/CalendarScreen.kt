

package ru.den.omg.screens.calendar

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.den.omg.navigations.bottomNavigation.BottomAppBar
import ru.den.omg.ui.theme.OmgTheme
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(navController: NavHostController) {
    OmgTheme {
        Scaffold(
            bottomBar = { BottomAppBar(navController = navController) },
            topBar = {
                TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent.copy(alpha = 0.8f)
                ),
                    title = { Text(text = "Мероприятия", fontSize = 35.sp) }
                )
            }
        ) {
            val calendar = Calendar.getInstance()
            calendar.set(2009, 3, 14) // add year, month (Jan), date

            // set the initial date
            val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)

            DatePicker(
                state = datePickerState,
                modifier = Modifier.padding(top = it.calculateTopPadding())
            )
        }
    }
}