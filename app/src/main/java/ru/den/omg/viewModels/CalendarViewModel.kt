package ru.den.omg.viewModels

import androidx.compose.material3.DatePickerState
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
import java.util.Calendar


class CalendarViewModel(val database: Mine_Data24) : ViewModel() {
    val datepick = Calendar.getInstance()


    val listItems = database.dao.getCalendar()
    var party by mutableStateOf("")
    var calendar: Calendar_Entity? = null
    var date by mutableStateOf("")


    fun insertItem() = viewModelScope.launch {
        val lesson = calendar?.copy(party = party, data = date) ?: Calendar_Entity(party = party, data = date)
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