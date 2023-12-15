package ru.den.omg


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.den.omg.navigations.NavGraph
import ru.den.omg.ui.theme.OmgTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : ComponentActivity() {
    @Inject lateinit var channel: NotificationChannel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         val notification = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notification.createNotificationChannel(channel)

        setContent {
            OmgTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color(0xFF7B68EE)
                )
                {
                    val navController = rememberNavController()
                    NavGraph(navController = navController, context = this)
                }
            }
        }
    }
}

