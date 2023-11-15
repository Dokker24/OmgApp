@file:OptIn(ExperimentalFoundationApi::class)

package ru.den.omg.navigations.tabNavigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun TabContent(tabs: List<TabItems>, pagerState: PagerState, navController: NavController) {
    HorizontalPager(state = pagerState) { page ->
        tabs[page].screen()
    }
}