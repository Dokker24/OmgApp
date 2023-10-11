package ru.den.omg.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friday_entity")
data class Friday_Entity(@PrimaryKey(true) val id: Int? = null, val lesson: String)