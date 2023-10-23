package ru.den.omg.viewModels

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
import ru.den.omg.data.entity.Thursday_Entity

class ThursdayViewModel(val database: Mine_Data24) : ViewModel() {
    val itemList = database.dao.getItemThu()
    var newText by mutableStateOf("")
    var thursday: Thursday_Entity? = null
    var newTimeBefore by mutableStateOf("8:00")
    var newTimeAfter by mutableStateOf("8:45")

    fun insertItem() = viewModelScope.launch {
        val lesson = thursday?.copy(lesson = newText) ?: Thursday_Entity(lesson = newText, time = "$newTimeBefore - $newTimeAfter")
        database.dao.insertItem(lesson)
        thursday = null
        newText = ""
    }

    fun deleteItem(item: Thursday_Entity) = viewModelScope.launch {
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
                return ThursdayViewModel(database) as T
            }
        }
    }
}