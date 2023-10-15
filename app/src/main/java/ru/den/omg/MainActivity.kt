package ru.den.omg

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.NotificationCompat
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import ru.den.omg.navigations.NavGraph
import ru.den.omg.ui.theme.OmgTheme
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val channel = NotificationChannel(
            "Time_Notivication",
            "Time",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(this, "Time_Notivication")
            .setContentTitle("Уведомление")
            .setContentText("Самый главный ден")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .build()
        denis(notificationManager, notification)
        

        setContent {
            OmgTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
}

fun denis(notificationManager: NotificationManager, notification: Notification) {
    notificationManager.notify(1337, notification)
}