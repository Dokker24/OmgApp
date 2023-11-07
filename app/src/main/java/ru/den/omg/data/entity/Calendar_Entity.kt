package ru.den.omg.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calendar_entity")
data class Calendar_Entity(
    @PrimaryKey(true) val id: Int? = null,
    val party: String,
    val data: String
)