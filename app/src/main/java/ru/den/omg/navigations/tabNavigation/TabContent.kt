@file:OptIn(ExperimentalFoundationApi::class)

package ru.den.omg.navigations.tabNavigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable

@Composable
fun TabContent(tabs: List<TabItems>, pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        tabs[page].screen()
    }
}