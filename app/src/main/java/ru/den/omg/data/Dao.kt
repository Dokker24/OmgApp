package ru.den.omg.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.den.omg.data.entity.Calendar_Entity
import ru.den.omg.data.entity.Friday_Entity
import ru.den.omg.data.entity.Monday_Entity
import ru.den.omg.data.entity.Saturday_Entity
import ru.den.omg.data.entity.Thursday_Entity
import ru.den.omg.data.entity.Tuesday_Entity
import ru.den.omg.data.entity.Wednesday_Entity

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(mondayEntity: Monday_Entity)
    @Delete
    suspend fun deleteItem(mondayEntity: Monday_Entity)
    @Query("SELECT * FROM monday_entity")
    fun getItem(): Flow<List<Monday_Entity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(tuesdayEntity: Tuesday_Entity)
    @Delete
    suspend fun deleteItem(tuesdayEntity: Tuesday_Entity)
    @Query("SELECT * FROM tuesday_entity")
    fun getItemTue(): Flow<List<Tuesday_Entity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(wednesdayEntity: Wednesday_Entity)
    @Delete
    suspend fun deleteItem(wednesdayEntity: Wednesday_Entity)
    @Query("SELECT * FROM wednesday_entity")
    fun getItemWed(): Flow<List<Wednesday_Entity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(thursdayEntity: Thursday_Entity)
    @Delete
    suspend fun deleteItem(thursdayEntity: Thursday_Entity)
    @Query("SELECT * FROM thursday_entity")
    fun getItemThu(): Flow<List<Thursday_Entity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(fridayEntity: Friday_Entity)
    @Delete
    suspend fun deleteItem(fridayEntity: Friday_Entity)
    @Query("SELECT * FROM friday_entity")
    fun getItemFri(): Flow<List<Friday_Entity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(saturdayEntity: Saturday_Entity)
    @Delete
    suspend fun deleteItem(saturdayEntity: Saturday_Entity)
    @Query("SELECT * FROM saturday_entity")
    fun getItemSat(): Flow<List<Saturday_Entity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(calendarEntity: Calendar_Entity)
    @Delete
    suspend fun deleteItem(calendarEntity: Calendar_Entity)
    @Query("SELECT * FROM calendar_entity")
    fun getCalendar(): Flow<List<Calendar_Entity>>
}