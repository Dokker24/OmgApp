package ru.den.omg.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.den.omg.App
import ru.den.omg.data.Mine_Data24
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeViewModel(database: Mine_Data24) : ViewModel() {
    val monday = database.dao.getItem()
    val tuesday = database.dao.getItemTue()
    val wednesday = database.dao.getItemWed()
    val thursday = database.dao.getItemThu()
    val friday = database.dao.getItemFri()
    val saturday = database.dao.getItemSat()



    fun checkDate() = viewModelScope.launch {
        when(getData().await()) {
            "пн" -> {
                Log.e("DataWeek", "Понедельник")
            }
            "вт" -> { Log.e("DataWeek", "Вторник") }
            "ср" -> { Log.e("DataWeek", "Среда") }
            "чт" -> { Log.e("DataWeek", "Четверг") }
            "пт" -> { Log.e("DataWeek", "Пятница") }
            "сб" -> { Log.e("DataWeek", "Суббота") }
            else -> { Log.e("DataWeek", "Воскресенье") }
        }
    }

    private fun getTime() = viewModelScope.async {
        val time = SimpleDateFormat("HH.mm.ss", Locale.getDefault()).format(Date())
        Log.d("Time", time)
        return@async time
    }


    private fun getData() = viewModelScope.async {
        val data = SimpleDateFormat("EE", Locale.getDefault()).format(Date())
        Log.e("Data", data)
        return@async data
    }

    companion object {
        val factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database = (checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as App).data
                return HomeViewModel(database) as T
            }
        }
    }
}