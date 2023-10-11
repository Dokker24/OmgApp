package ru.den.omg.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import ru.den.omg.App
import ru.den.omg.data.Mine_Data24
import ru.den.omg.data.entity.Monday_Entity


class MainViewModel(val database: Mine_Data24) : ViewModel() {
    val itemList = database.dao.getItem()
    var newText by mutableStateOf("")
    var monday: Monday_Entity? = null

    fun insertItem() = viewModelScope.launch {
        val lesson = monday?.copy(lesson = newText) ?: Monday_Entity(lesson = newText)
        database.dao.insertItem(lesson)
        monday = null
        newText = ""
    }

    fun deleteItem(item: Monday_Entity) = viewModelScope.launch {
        database.dao.deleteItem(item)
    }

    companion object {
        val factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).data
                return MainViewModel(database) as T
            }
        }
    }
}