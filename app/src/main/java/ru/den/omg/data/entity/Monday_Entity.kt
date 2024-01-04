package ru.den.omg.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monday_entity")
data class Monday_Entity(
    @PrimaryKey(true) val id: Int? = null,
    override val lesson: String,
    @ColumnInfo(defaultValue = "")
    override val time: String = ""
) : Week_Entity(lesson, time)