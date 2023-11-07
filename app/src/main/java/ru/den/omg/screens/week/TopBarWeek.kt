package ru.den.omg.screens.week


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.den.omg.navigations.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWeek(navController: NavController, title: String) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navController.navigate(Screens.List.route) }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "left")
            }
        },
        title = { Text(text = title, fontSize = 27.sp) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent.copy(alpha = 0.7f)
        )
    )
}