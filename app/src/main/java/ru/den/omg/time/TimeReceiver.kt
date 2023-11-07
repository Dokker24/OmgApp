package ru.den.omg.time



import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import ru.den.omg.R

class TimeReceiver : BroadcastReceiver() {
    /*
    Я ден
     */

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(context, "Time_Notivication")
            .setContentTitle("Скоро кончится ${intent.getStringExtra("Lesson")}")
            .setContentText("Поэтому нужно закругляться :)")
            .setSmallIcon(R.drawable.settings)
            .build()
        notificationManager.notify(intent.getIntExtra("Den", 1337), notification)
    }



}