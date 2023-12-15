package ru.den.omg.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.den.omg.data.entity.Calendar_Entity
import ru.den.omg.data.entity.Monday_Entity
import ru.den.omg.data.entity.Friday_Entity
import ru.den.omg.data.entity.Saturday_Entity
import ru.den.omg.data.entity.Thursday_Entity
import ru.den.omg.data.entity.Tuesday_Entity
import ru.den.omg.data.entity.Wednesday_Entity
import ru.den.omg.screens.calendar.CalendarScreen

@Database(
    entities = [
        Monday_Entity::class,
    Tuesday_Entity::class,
    Wednesday_Entity::class,
    Thursday_Entity::class,
    Friday_Entity::class,
    Saturday_Entity::class,
    Calendar_Entity::class
               ],
    version = 3,
    autoMigrations = [
        AutoMigration(2, 3)
    ],
    exportSchema = true
    )

abstract class Mine_Data24 : RoomDatabase() {

    abstract val dao: Dao

    companion object {
        fun createDatabase(context: Context): Mine_Data24 {
            return Room.databaseBuilder(
                context,
                Mine_Data24::class.java,
                "Weeks.db"
            ).build()
        }
    }
}