package ru.den.omg.navigations.bottomNavigation



import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun BottomAppBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val list = listOf(
        BottomIcons.Home_Screen,
        BottomIcons.List_Screen,
        BottomIcons.Calendar_Screen
        )
    var selectedItem by remember { mutableIntStateOf(0) }


    NavigationBar(
        containerColor = Color.Transparent.copy(alpha = 0.7f),
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
        ) {
        list.forEachIndexed { index, item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Blue
                ),
                selected = currentRoute == item.route,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)
                    },
                icon = { Icon(painter = painterResource(id = item.image), contentDescription = "$index") },
                label = { Text(text = item.title, fontSize = 12.sp) }
                )
        }
    }
}