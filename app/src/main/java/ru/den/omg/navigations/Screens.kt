package ru.den.omg.navigations

sealed class Screens(val route: String) {
    object Home: Screens("home_screen")
    object Week: Screens("week_screen")
    object List: Screens("list_screen")
    object Settings: Screens("settings_screen")
    object Tuesday: Screens("tuesday_screen")
    object Wednesday: Screens("wednesday_screen")
    object Thursday: Screens("thursday_screen")
    object Friday: Screens("friday_screen")
    object Saturday: Screens("saturday_screen")
    object Calendar: Screens("calendar_screen")
}