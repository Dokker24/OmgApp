package ru.den.omg.viewModels

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
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import ru.den.omg.App
import ru.den.omg.data.Mine_Data24
import ru.den.omg.data.entity.Wednesday_Entity

class WednesdayViewModel(val database: Mine_Data24) : ViewModel() {
    val itemList = database.dao.getItemWed()
    var newText by mutableStateOf("")
    var wed: Wednesday_Entity? = null
    var newTimeBefore by mutableStateOf("8:00")
    var newTimeAfter by mutableStateOf("8:45")
    val rainbowColors = listOf(Color.Cyan, Color.Green, Color.Red, Color.Yellow)

    fun insertItem() = viewModelScope.launch {
        val lesson = wed?.copy(lesson = newText) ?: Wednesday_Entity(lesson = newText, time = "$newTimeBefore - $newTimeAfter")
        database.dao.insertItem(lesson)
        wed = null
        newText = ""
    }

    fun deleteItem(item: Wednesday_Entity) = viewModelScope.launch {
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
                return WednesdayViewModel(database) as T
            }
        }
    }
}