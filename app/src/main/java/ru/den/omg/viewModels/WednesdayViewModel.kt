package ru.den.omg.viewModels

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import ru.den.omg.App
import ru.den.omg.data.Mine_Data24
import ru.den.omg.data.entity.Monday_Entity
import ru.den.omg.data.entity.Wednesday_Entity
import ru.den.omg.time.TimeForDatabase
import ru.den.omg.time.TimeReceiver
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class WednesdayViewModel @Inject constructor(val database: Mine_Data24) : ViewModel() {
    val itemList = database.dao.getItemWed()
    var newText by mutableStateOf("")
    var wed: Wednesday_Entity? = null
    var newTimeBefore by mutableStateOf("8:00")
    var newTimeAfter by mutableStateOf("8:45")
    val rainbowColors = listOf(Color.Cyan, Color.Green, Color.Red, Color.Yellow)

    private val calendar = Calendar.getInstance()

    fun insertItem() = viewModelScope.launch {
        val lesson = wed?.copy(lesson = newText) ?: Wednesday_Entity(lesson = newText, time = "$newTimeBefore - $newTimeAfter")
        database.dao.insertItem(lesson)
        wed = null
        newText = ""
    }

    fun deleteItem(item: Wednesday_Entity) = viewModelScope.launch {
        database.dao.deleteItem(item)
    }


    @SuppressLint("ScheduleExactAlarm")
    fun sendNotify(context: Context, item: Wednesday_Entity) = viewModelScope.launch {
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