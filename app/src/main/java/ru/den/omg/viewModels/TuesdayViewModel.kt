package ru.den.omg.viewModels

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.den.omg.App
import ru.den.omg.data.Mine_Data24
import ru.den.omg.data.entity.Tuesday_Entity
import ru.den.omg.time.TimeForDatabase
import ru.den.omg.time.TimeReceiver
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class TuesdayViewModel @Inject constructor(val database: Mine_Data24) : ViewModel() {
    val itemList = database.dao.getItemTue()
    var newText by mutableStateOf("")
    var newTimeBefore by mutableStateOf("8:00")
    var newTimeAfter by mutableStateOf("8:45")
    var tuesday: Tuesday_Entity? = null

    private val calendar = Calendar.getInstance()

    fun insertItem() = viewModelScope.launch {
        val lesson = tuesday?.copy(lesson = newText) ?: Tuesday_Entity(lesson = newText, time = "$newTimeBefore - $newTimeAfter")
        database.dao.insertItem(lesson)
        tuesday = null
        newText = ""
    }

    fun deleteItem(item: Tuesday_Entity) = viewModelScope.launch {
        database.dao.deleteItem(item)
    }

    @SuppressLint("ScheduleExactAlarm")
    fun sendNotify(context: Context, item: Tuesday_Entity) = viewModelScope.launch {
        try {
            calendar.set(Calendar.HOUR_OF_DAY, TimeForDatabase.timeAfterIntHour(item.time.split('-')[1].trim()))
            calendar.set(Calendar.MINUTE, TimeForDatabase.timeAfterIntMinute(item.time.split('-')[1].trim()))

            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TimeReceiver::class.java)
            intent.putExtra("Lesson", item.lesson)
            intent.putExtra("Den", item.id)
            val pendingIntent = PendingIntent.getBroadcast(context, item.id ?: 1, intent, PendingIntent.FLAG_IMMUTABLE)
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )

            Toast.makeText(context, "Уведомление зазвучит за 5 минут до конца урока", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Ошибка, проверьте введённые значения", Toast.LENGTH_SHORT).show()
        }
    }
}