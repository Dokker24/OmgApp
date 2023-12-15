package ru.den.omg.time



import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import dagger.hilt.android.AndroidEntryPoint
import ru.den.omg.R
import javax.inject.Inject

@AndroidEntryPoint
class TimeReceiver : BroadcastReceiver() {
    /*
    Я ден
     */

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, "Time_Notivication")
            .setContentTitle("Скоро кончится ${intent.getStringExtra("Lesson")}")
            .setContentText("Поэтому нужно закругляться :)")
            .setSmallIcon(R.drawable.settings)
            .build()
        notificationManager.notify(intent.getIntExtra("Den", 1337), notification)
    }
}