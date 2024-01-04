package ru.den.omg.navigations


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.den.omg.screens.ListMonth
import ru.den.omg.screens.ListWeek
import ru.den.omg.screens.calendar.CalendarScreen
import ru.den.omg.screens.home.HomeScreen
import ru.den.omg.screens.settings.Settings_Screen
import ru.den.omg.screens.splash.AnimatedSplashScreen
import ru.den.omg.screens.week.DayOfWeek
import ru.den.omg.screens.week.DayOfWeekViewModel
import ru.den.omg.screens.week.Friday_Week




@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: DayOfWeekViewModel = hiltViewModel()
) {

    val list = viewModel.listMon

    NavHost(navController = navController,
        startDestination = Screens.Splash.route) {

        // Домашний экран
        composable(Screens.Home.route) {
            HomeScreen(navController)
        }

        // Экран внесения предметов
        composable(Screens.Week.route) {
            DayOfWeek(navController = navController, list = list, title = "Понедельник")
        }

        // Экран расписания
        composable(Screens.List.route) {
            ru.den.omg.screens.list.List(navController)
        }

        // экран настроек
        composable(Screens.Settings.route) {
            Settings_Screen(navController = navController)
        }

//        composable(Screens.Tuesday.route) {
//            Tuesday_Week(navController = navController)
//        }

//        composable(Screens.Wednesday.route) {
//            Wednesday_Week(navController = navController)
//        }

//        composable(Screens.Thursday.route) {
//            Thursday_Week(navController)
//        }

        composable(Screens.Friday.route) {
            Friday_Week(navController)
        }

        composable(Screens.Calendar.route) {
            CalendarScreen(navController)
        }

//        composable(Screens.Saturday.route) {
//            Saturday_Week(navController = navController)
//        }

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