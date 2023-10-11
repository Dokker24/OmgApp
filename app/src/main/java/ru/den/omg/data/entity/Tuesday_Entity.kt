package ru.den.omg.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tuesday_entity")
data class Tuesday_Entity(
    @PrimaryKey(true)
    val id: Int? = null,
    val lesson: String
)