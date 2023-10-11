package ru.den.omg.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wednesday_entity")
data class Wednesday_Entity(@PrimaryKey(true) val id: Int? = null, val lesson: String)