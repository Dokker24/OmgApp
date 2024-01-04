package ru.den.omg.screens.week

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.den.omg.data.Mine_Data24
import ru.den.omg.data.entity.Monday_Entity
import ru.den.omg.data.entity.Week_Entity
import javax.inject.Inject

@HiltViewModel
class DayOfWeekViewModel @Inject constructor(
    val data: Mine_Data24
) : ViewModel() {
    val listMon = data.dao.getItem()
    val listTue = data.dao.getItemTue()
    val listWed = data.dao.getItemWed()
    val listThu = data.dao.getItemThu()


    var newText by mutableStateOf("")
    var newTimeBefore by mutableStateOf("8:00")
    var newTimeAfter by mutableStateOf("8:45")
    var day: Week_Entity? = null

    fun insertItem() = viewModelScope.launch {

    }

    fun deleteItem(item: Week_Entity) = viewModelScope.launch {
        data.dao.deleteItem(item as Monday_Entity)
    }

    fun sendNotify(item: Week_Entity, context: Context) {

    }
}