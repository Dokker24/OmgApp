package ru.den.omg.time

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TimeService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}