package ru.den.omg.navigations.tabNavigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@Composable
fun Tab(tabs: List<TabItems>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()


    // OR ScrollableTabRow()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Transparent.copy(alpha = 0.7f),
        contentColor = Color.White,
        ) {
        tabs.forEachIndexed { index, tab ->
            // OR Tab()
            LeadingIconTab (
                text = { Text(tab.title) },
                selected = pagerState.currentPage == index,
                icon = { Icon(painterResource(id = tab.icon), contentDescription = "tabIcon") },
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

