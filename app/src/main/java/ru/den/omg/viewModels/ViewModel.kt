package ru.den.omg.viewModels

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import ru.den.omg.App
import ru.den.omg.data.Mine_Data24
import ru.den.omg.data.entity.Monday_Entity
import ru.den.omg.time.TimeForDatabase
import ru.den.omg.time.TimeReceiver
import java.util.Calendar


class MainViewModel(val database: Mine_Data24) : ViewModel() {
    val itemList = database.dao.getItem()
    var newText by mutableStateOf("")
    var newTimeBefore by mutableStateOf("8:00")
    var newTimeAfter by mutableStateOf("8:45")


    private val calendar = Calendar.getInstance()
    private var monday: Monday_Entity? = null



    fun insertItem() = viewModelScope.launch {
        val lesson = monday?.copy(lesson = newText) ?: Monday_Entity(lesson = newText, time = "$newTimeBefore-$newTimeAfter")
        database.dao.insertItem(lesson)
        monday = null
        newText = ""

    }


    fun deleteItem(item: Monday_Entity) = viewModelScope.launch {
        database.dao.deleteItem(item)
    }


    @SuppressLint("ScheduleExactAlarm")
    fun sendNotify(context: Context, item: Monday_Entity) = viewModelScope.launch {
        calendar.set(Calendar.HOUR_OF_DAY, TimeForDatabase.timeAfterIntHour(item.time.split('-')[1]))
        calendar.set(Calendar.MINUTE, TimeForDatabase.timeAfterIntMinute(item.time.split('-')[1]))

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, TimeReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, item.id ?: 1, intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }

    fun unNotify() = viewModelScope.launch {

    }

    companion object {
        val factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).data
                return MainViewModel(database) as T
            }
        }
        fun isNumeric(str: String): Boolean {
            return try {
                str.toDouble()
                true
            } catch (e: NumberFormatException) {
                false
            }
        }
    }
}

