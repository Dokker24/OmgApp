package ru.den.omg.navigations

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.den.omg.screens.ListMonth
import ru.den.omg.screens.ListWeek
import ru.den.omg.screens.calendar.CalendarScreen
import ru.den.omg.screens.home.HomeScreen
import ru.den.omg.screens.settings.Settings_Screen
import ru.den.omg.screens.splash.AnimatedSplashScreen
import ru.den.omg.screens.week.Friday_Week
import ru.den.omg.screens.week.Monday_Week
import ru.den.omg.screens.week.Saturday_Week
import ru.den.omg.screens.week.Thursday_Week
import ru.den.omg.screens.week.Tuesday_Week
import ru.den.omg.screens.week.Wednesday_Week



//Не забыть отправлять контекст экрана в другие скрины.
@Composable
fun NavGraph(navController: NavHostController, context: Context) {
    NavHost(navController = navController,
        startDestination = Screens.Splash.route) {

        // Домашний экран
        composable(Screens.Home.route) {
            HomeScreen(navController)
        }

        // Экран внесения предметов
        composable(Screens.Week.route) {
            Monday_Week(navController = navController, context = context)
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
            Tuesday_Week(navController = navController, context)
        }

        composable(Screens.Wednesday.route) {
            Wednesday_Week(navController = navController, context)
        }

        composable(Screens.Thursday.route) {
            Thursday_Week(navController, context)
        }

        composable(Screens.Friday.route) {
            Friday_Week(navController, context)
        }

        composable(Screens.Calendar.route) {
            CalendarScreen(navController)
        }

        composable(Screens.Saturday.route) {
            Saturday_Week(navController, context)
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