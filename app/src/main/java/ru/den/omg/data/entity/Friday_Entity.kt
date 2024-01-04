package ru.den.omg.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.den.omg.time.TimeForDatabase

@Entity(tableName = "friday_entity")
data class Friday_Entity(@PrimaryKey(true) val id: Int? = null, override val lesson: String,
                         @ColumnInfo(defaultValue = "") override val time: String = "") : Week_Entity(lesson, time)