package ru.den.omg.time

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import ru.den.omg.data.Mine_Data24
import ru.den.omg.data.entity.Monday_Entity
import java.util.Date


// Класс не используется, но выбрасывать как-то жалко
class Time(val data: Mine_Data24) : ViewModel() {

    val listMonday = data.dao.getItem()
    val listTuesday = data.dao.getItemTue()
    val listWednesday = data.dao.getItemWed()
    val listThursday = data.dao.getItemThu()
    val listFriday = data.dao.getItemFri()
    val listSaturday = data.dao.getItemSat()


    suspend fun time() {
        getList().await().forEach {
            it.forEach {text ->
                Log.d("Туда-сюда", text.toString())
            }
        }
    }

    suspend fun getList() = viewModelScope.async {
        when (getData()) {
            "пн" -> { getMon() }
            "вт" -> { getTue() }
            "ср" -> { getWed() }
            "чт" -> { getThu() }
            "пт" -> { getFri() }
            else -> {getSat()}
        }
    }



    suspend fun getMon() = listMonday.toList()
    suspend fun getTue() = listTuesday.toList()
    suspend fun getWed() = listWednesday.toList()
    suspend fun getThu() = listThursday.toList()
    suspend fun getFri() = listFriday.toList()
    suspend fun getSat() = listSaturday.toList()



    @SuppressLint("SimpleDateFormat")
    private fun getTime(): String {
        val time = SimpleDateFormat("HH:mm:ss").format(Date())
        Log.e("Толькоооооо", time)
        return time
    }

    @SuppressLint("SimpleDateFormat")
    private fun getData(): String {
        val data = SimpleDateFormat("EEE").format(Date())
        Log.e("Рюмка водки", data)
        return data
    }

}