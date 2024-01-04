package ru.den.omg.converter




import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Top(title: String) {
    androidx.compose.material3.TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.Transparent.copy(alpha = 0.7f)
    ),
        title = { Text(text = title, fontSize = 35.sp, color = Color.White) }
    )
}