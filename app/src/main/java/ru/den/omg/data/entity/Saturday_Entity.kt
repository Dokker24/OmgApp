package ru.den.omg.data.entity;

import androidx.room.ColumnInfo
import androidx.room.Entity;import androidx.room.PrimaryKey;
import ru.den.omg.time.TimeForDatabase

@Entity(tableName = "saturday_entity") data class Saturday_Entity(@PrimaryKey(true) val id: Int? = null, val lesson: String,
                                                                  @ColumnInfo(defaultValue = "")val time: String = "")