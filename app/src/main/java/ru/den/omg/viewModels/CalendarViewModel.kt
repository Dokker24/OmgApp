@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package ru.den.omg.viewModels

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import ru.den.omg.App
import ru.den.omg.data.Mine_Data24
import ru.den.omg.data.entity.Calendar_Entity
import ru.den.omg.data.entity.Monday_Entity
import ru.den.omg.time.TimeForDatabase
import ru.den.omg.time.TimeReceiver
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale
import java.util.TimeZone


@Suppress("OPT_IN_USAGE_FUTURE_ERROR")
class CalendarViewModel(val database: Mine_Data24) : ViewModel() {
    val datepick = Calendar.getInstance()

    var expanded by mutableStateOf(false)

    val sdf = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
    val gregorianCalendar = GregorianCalendar(TimeZone.getTimeZone("RU"))


    private fun getDateToString(time: Long?): String {
        gregorianCalendar.timeInMillis = time ?: 1111111111111
        return sdf.format(gregorianCalendar.time)
    }

    val listItems = database.dao.getCalendar()
    var party by mutableStateOf("")
    var calendar: Calendar_Entity? = null
    @OptIn(ExperimentalMaterial3Api::class)
    var date = DatePickerState(
        initialSelectedDateMillis = datepick.timeInMillis,
        initialDisplayedMonthMillis = datepick.timeInMillis,
        yearRange = IntRange(1900, 2100),
        initialDisplayMode = DisplayMode.Picker
        )




    @OptIn(ExperimentalMaterial3Api::class)
    fun insertItem() = viewModelScope.launch {
        val lesson = calendar?.copy(party = party, data = date.toString()) ?: Calendar_Entity(party = party, data = getDateToString(date.selectedDateMillis))
        database.dao.insertItem(lesson)
        calendar = null
        party = ""
    }






    fun deleteItem(item: Calendar_Entity) = viewModelScope.launch {
        database.dao.deleteItem(item)
    }

    companion object {
        val factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database = (checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as App).data
                return CalendarViewModel(database) as T
            }
        }
    }
}