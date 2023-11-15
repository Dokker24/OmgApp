package ru.den.omg.navigations.tabNavigation

import androidx.compose.runtime.Composable
import ru.den.omg.screens.ListMonth
import ru.den.omg.screens.ListWeek

typealias ComposableFun = @Composable () -> Unit

sealed class TabItems(val title: String, /*val route: String*/ val screen: ComposableFun) {
    object ListWeek: TabItems("Расписание", /*Screens.ListWeek.route*/ { ListWeek() })

    object ListMonth: TabItems("Мероприятия", { ListMonth() })
}