package ru.den.omg.screens.week

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
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.den.omg.data.Mine_Data24
import ru.den.omg.data.entity.Friday_Entity
import ru.den.omg.data.entity.Monday_Entity
import ru.den.omg.data.entity.Saturday_Entity
import ru.den.omg.data.entity.Thursday_Entity
import ru.den.omg.data.entity.Tuesday_Entity
import ru.den.omg.data.entity.Wednesday_Entity
import ru.den.omg.data.entity.Week_Entity
import ru.den.omg.time.TimeForDatabase
import ru.den.omg.time.TimeReceiver
import java.util.Calendar
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class DayOfWeekViewModel @Inject constructor(
    val data: Mine_Data24
) : ViewModel() {
    val listMon = data.dao.getItem()
    val listTue = data.dao.getItemTue()
    val listWed = data.dao.getItemWed()
    val listThu = data.dao.getItemThu()
    val listFri = data.dao.getItemFri()
    val listSat = data.dao.getItemSat()


    var newText by mutableStateOf("")
    var newTimeBefore by mutableStateOf("8:00")
    var newTimeAfter by mutableStateOf("8:45")

    var monday: Monday_Entity? = null
    var tuesday: Tuesday_Entity? = null
    var wednesday: Wednesday_Entity? = null
    var thursday: Thursday_Entity? = null
    var friday: Friday_Entity? = null
    var saturday: Saturday_Entity? = null

    private val calendar = Calendar.getInstance()

    fun insertItem(title: String) = viewModelScope.launch {
        when(title) {
            DayInWeek.Понедельник.toString() -> {
                val lesson = monday?.copy(lesson = newText, time = "$newTimeBefore - $newTimeAfter") ?: Monday_Entity(lesson = newText, time = "$newTimeBefore - $newTimeAfter")
                data.dao.insertItem(lesson)
            }
            DayInWeek.Вторник.toString() -> {
                val lesson = tuesday?.copy(lesson = newText, time = "$newTimeBefore - $newTimeAfter") ?: Tuesday_Entity(lesson = newText, time = "$newTimeBefore - $newTimeAfter")
                data.dao.insertItem(lesson)
            }
            DayInWeek.Среда.toString() -> {
                val lesson = wednesday?.copy(lesson = newText, time = "$newTimeBefore - $newTimeAfter") ?: Wednesday_Entity(lesson = newText, time = "$newTimeBefore - $newTimeAfter")
                data.dao.insertItem(lesson)
            }
            DayInWeek.Четверг.toString() -> {
                val lesson = thursday?.copy(lesson = newText, time = "$newTimeBefore - $newTimeAfter") ?: Thursday_Entity(lesson = newText, time = "$newTimeBefore - $newTimeAfter")
                data.dao.insertItem(lesson)
            }
            DayInWeek.Пятница.toString() -> {
                val lesson = friday?.copy(lesson = newText, time = "$newTimeBefore - $newTimeAfter") ?: Friday_Entity(lesson = newText, time = "$newTimeBefore - $newTimeAfter")
                data.dao.insertItem(lesson)
            }
            DayInWeek.Суббота.toString() -> {
                val lesson = saturday?.copy(lesson = newText, time = "$newTimeBefore - $newTimeAfter") ?: Saturday_Entity(lesson = newText, time = "$newTimeBefore - $newTimeAfter")
                data.dao.insertItem(lesson)
            }
            else -> {
                val lesson = monday?.copy(lesson = newText, time = "$newTimeBefore - $newTimeAfter") ?: Monday_Entity(lesson = newText, time = "$newTimeBefore - $newTimeAfter")
                data.dao.insertItem(lesson)
            }
        }
        monday = null
        tuesday = null
        wednesday = null
        thursday = null
        friday = null
        saturday = null
        newText = ""
    }

    fun deleteItem(item: Week_Entity, title: String, context: Context) = viewModelScope.launch {
        when(title) {
            DayInWeek.Понедельник.toString() -> data.dao.deleteItem(item as Monday_Entity)
            DayInWeek.Вторник.toString() -> data.dao.deleteItem(item as Tuesday_Entity)
            DayInWeek.Среда.toString() -> data.dao.deleteItem(item as Wednesday_Entity)
            DayInWeek.Четверг.toString() -> data.dao.deleteItem(item as Thursday_Entity)
            DayInWeek.Пятница.toString() -> data.dao.deleteItem(item as Friday_Entity)
            DayInWeek.Суббота.toString() -> data.dao.deleteItem(item as Saturday_Entity)
            else -> Toast.makeText(context, "Ошибка, попробуйте позже", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("ScheduleExactAlarm")
    fun sendNotify(item: Week_Entity, context: Context) = viewModelScope.launch {
        try {
            calendar.set(Calendar.HOUR_OF_DAY, TimeForDatabase.timeAfterIntHour(item.time.split('-')[1].trim()))
            calendar.set(Calendar.MINUTE, TimeForDatabase.timeAfterIntMinute(item.time.split('-')[1].trim()))

            val id = Random.nextInt(200)

            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TimeReceiver::class.java)
            intent.putExtra("Lesson", item.lesson)
            intent.putExtra("Den", id)
            val pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_IMMUTABLE)
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )

            Toast.makeText(context, "Уведомление зазвучит за 5 минут до конца урока", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Ошибка, проверьте введённые значения", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
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