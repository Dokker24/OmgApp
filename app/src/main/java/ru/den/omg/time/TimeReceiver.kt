package ru.den.omg.time

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import ru.den.omg.R
import ru.den.omg.data.entity.Monday_Entity
import java.util.Calendar

class TimeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(context, "Time_Notivication")
            .setContentTitle("Уведомление")
            .setContentText("Урок скоро кончится")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .build()
        notificationManager.notify(1337, notification)
    }
}