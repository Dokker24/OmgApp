package ru.den.omg.time

import android.icu.text.SimpleDateFormat
import android.util.Log
import java.util.Date


class Time {
    companion object {
        fun time() {
            val time = SimpleDateFormat("HH:mm:ss").format(Date())
            Log.e("Толькоооооо", time.toString())
        }
    }

}