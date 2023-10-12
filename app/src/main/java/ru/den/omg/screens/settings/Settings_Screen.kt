package ru.den.omg.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.den.omg.navigations.bottomNavigation.BottomAppBar
import ru.den.omg.ui.theme.OmgTheme

@Composable
fun Settings_Screen(navController: NavController) {
    OmgTheme {
        Surface {
            var switch by remember { mutableStateOf(false) }
            Scaffold(
                bottomBar = { BottomAppBar(navController = navController) }
            ) {
                Column {
                    Text(text = "Настройки", fontSize = 35.sp, modifier = Modifier.padding(start = 15.dp, top = 5.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Оповещения о конце урока", fontSize = 15.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 20.dp))
                        Spacer(modifier = Modifier.weight(1f))
                        Switch(checked = switch, onCheckedChange = { switch = !switch }, modifier = Modifier.padding(5.dp))
                    }
                }
                it
            }
        }
    }
}

