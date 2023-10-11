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
import ru.den.omg.data.entity.Wednesday_Entity

class WednesdayViewModel(val database: Mine_Data24) : ViewModel() {
    val itemList = database.dao.getItemWed()
    var newText by mutableStateOf("")
    var wed: Wednesday_Entity? = null

    fun insertItem() = viewModelScope.launch {
        val lesson = wed?.copy(lesson = newText) ?: Wednesday_Entity(lesson = newText)
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