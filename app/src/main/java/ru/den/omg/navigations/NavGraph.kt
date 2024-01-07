package ru.den.omg.navigations


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.den.omg.screens.month.ListMonth
import ru.den.omg.screens.week.ListWeek
import ru.den.omg.screens.calendar.CalendarScreen
import ru.den.omg.screens.home.HomeScreen
import ru.den.omg.screens.settings.Settings_Screen
import ru.den.omg.screens.splash.AnimatedSplashScreen
import ru.den.omg.screens.week.DayInWeek
import ru.den.omg.screens.week.DayOfWeek
import ru.den.omg.screens.week.DayOfWeekViewModel




@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: DayOfWeekViewModel = hiltViewModel()
) {

    val listMon = viewModel.listMon
    val listTue = viewModel.listTue
    val listWed = viewModel.listWed
    val listThu = viewModel.listThu
    val listFri = viewModel.listFri
    val listSat = viewModel.listSat

    NavHost(navController = navController,
        startDestination = Screens.Splash.route) {

        // Домашний экран
        composable(Screens.Home.route) {
            HomeScreen(navController)
        }

        // Экран внесения предметов
        composable(Screens.Week.route) {
            DayOfWeek(navController = navController, list = listMon, title = DayInWeek.Понедельник.toString())
            Log.d("composable", DayInWeek.Понедельник.toString())
        }

        // Экран расписания
        composable(Screens.List.route) {
            ru.den.omg.screens.list.List(navController)
        }

        // экран настроек
        composable(Screens.Settings.route) {
            Settings_Screen(navController = navController)
        }

        composable(Screens.Tuesday.route) {
            DayOfWeek(navController = navController, list = listTue, title = DayInWeek.Вторник.toString())
        }

        composable(Screens.Wednesday.route) {
            DayOfWeek(navController = navController, list = listWed, title = DayInWeek.Среда.toString())
        }

        composable(Screens.Thursday.route) {
            DayOfWeek(navController, list = listThu, title = DayInWeek.Четверг.toString())
        }

        composable(Screens.Friday.route) {
            DayOfWeek(navController, list = listFri, title = DayInWeek.Пятница.toString())
        }

        composable(Screens.Calendar.route) {
            CalendarScreen(navController)
        }

        composable(Screens.Saturday.route) {
            DayOfWeek(navController = navController, list = listSat, title = DayInWeek.Суббота.toString())
        }

        composable(Screens.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }

        composable(Screens.ListMonth.route) {
            ListMonth()
        }

        composable(Screens.ListWeek.route) {
            ListWeek()
        }

    }
}