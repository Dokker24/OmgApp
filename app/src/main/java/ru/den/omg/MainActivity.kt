package ru.den.omg

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import ru.den.omg.navigations.NavGraph
import ru.den.omg.screens.splash.AnimatedSplashScreen
import ru.den.omg.time.TimeReceiver
import ru.den.omg.ui.theme.OmgTheme
import java.util.Calendar
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val channel = NotificationChannel(
            "Time_Notivication",
            "Time",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

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

