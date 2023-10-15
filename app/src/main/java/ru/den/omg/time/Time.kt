package ru.den.omg.time

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.Date


class Time : ViewModel() {





    val sizeLesson = when(getData()) {
        "пн" -> {  }
        "вс" -> {  }
        else -> {  }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTime(): String {
        val time = SimpleDateFormat("HH:mm:ss").format(Date())
        Log.e("Толькоооооо", time)
        return time
    }

    @SuppressLint("SimpleDateFormat")
    private fun getData(): String {
        val data = SimpleDateFormat("EEE").format(Date())
        Log.e("Рюмка водки", data)
        return data
    }

}