package ru.den.omg.navigations.bottomNavigation

import ru.den.omg.R


sealed class BottomIcons(val title: String, val image: Int, val route: String) {
    object Home_Screen: BottomIcons("Главная", R.drawable.baseline_home_24, "home_screen")
    object List_Screen: BottomIcons("Расписание", R.drawable.list, "list_screen")
    object Calendar_Screen: BottomIcons("Мероприятие", R.drawable.calendar, "calendar_screen")
    object Settings_Screen: BottomIcons("Настройки", R.drawable.settings, "settings_screen")
}
