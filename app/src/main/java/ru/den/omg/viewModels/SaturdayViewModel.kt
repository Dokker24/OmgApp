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
import ru.den.omg.data.entity.Saturday_Entity

class SaturdayViewModel(val database: Mine_Data24) : ViewModel() {
    val itemList = database.dao.getItemSat()
    var newText by mutableStateOf("")
    var saturday: Saturday_Entity? = null

    fun insertItem() = viewModelScope.launch {
        val lesson = saturday?.copy(lesson = newText) ?: Saturday_Entity(lesson = newText)
        database.dao.insertItem(lesson)
        saturday = null
        newText = ""
    }

    fun deleteItem(item: Saturday_Entity) = viewModelScope.launch {
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
                return SaturdayViewModel(database) as T
            }
        }
    }
}