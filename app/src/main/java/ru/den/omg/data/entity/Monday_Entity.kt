package ru.den.omg.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monday_entity")
data class Monday_Entity(
    @PrimaryKey(true) val id: Int? = null,
    val lesson: String
)