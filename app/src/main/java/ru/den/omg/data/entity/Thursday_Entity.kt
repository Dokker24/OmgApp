package ru.den.omg.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "thursday_entity")
data class Thursday_Entity(@PrimaryKey(true) val id: Int? = null, val lesson: String)