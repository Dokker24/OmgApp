@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package ru.den.omg.screens.calendar


import android.content.Context
import android.widget.Toast
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.den.omg.data.Mine_Data24
import ru.den.omg.data.entity.Calendar_Entity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject


@HiltViewModel
class CalendarViewModel @Inject constructor(val database: Mine_Data24) : ViewModel() {
    private val datepick = Calendar.getInstance()


    private val sdf = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
   private val gregorianCalendar = GregorianCalendar(TimeZone.getTimeZone("RU"))


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
    fun insertItem(context: Context) = viewModelScope.launch {
        try {
            val lesson = calendar?.copy(party = party, data = date.toString()) ?: Calendar_Entity(party = party, data = getDateToString(date.selectedDateMillis))
            database.dao.insertItem(lesson)
            calendar = null
            party = ""
            Toast.makeText(context, "напоминание создано", Toast.LENGTH_SHORT).show()
        } catch(e: Exception) {
            Toast.makeText(context, "Что-то пошло не так :(", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    /*******************
     * Скоро появится...
     *******************/

//    fun sendNotify(context: Context) = viewModelScope.launch {
//        try {
//            datepick.set(Calendar.DAY_OF_YEAR)
//        } catch (e: Exception) {
//            Toast.makeText(context, "Что-то пошло не так :(", Toast.LENGTH_SHORT).show()
//            e.printStackTrace()
//        }
//    }



    fun deleteItem(item: Calendar_Entity) = viewModelScope.launch {
        database.dao.deleteItem(item)
    }
}