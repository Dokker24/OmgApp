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
import java.util.Calendar

class TimeReceiver : BroadcastReceiver() {
    val calendar = Calendar.getInstance()

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(context, "Time_Notivication")
            .setContentTitle("Уведомление")
            .setContentText("Урок скоро кончится")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .build()
        notificationManager.notify(1337, notification)
    }

    @SuppressLint("ScheduleExactAlarm")
    fun startAlarm(context: Context, ) {

        calendar.set(Calendar.HOUR_OF_DAY, 15)
        calendar.set(Calendar.MINUTE, 0)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, TimeReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        alarmManager.cancel(pendingIntent)
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }

}