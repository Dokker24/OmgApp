package ru.den.omg.navigations.tabNavigation

import androidx.compose.runtime.Composable
import ru.den.omg.R
import ru.den.omg.screens.ListMonth
import ru.den.omg.screens.ListWeek

typealias ComposableFun = @Composable () -> Unit

sealed class TabItems(val title: String, /*val route: String*/ val icon: Int, val screen: ComposableFun) {
    object ListWeek: TabItems("Расписание", R.drawable.baseline_view_list, { ListWeek() })
    object ListMonth: TabItems("Мероприятия", R.drawable.baseline_view_compact, { ListMonth() })
}