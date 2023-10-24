package ru.den.omg.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.den.omg.data.entity.Monday_Entity
import ru.den.omg.data.entity.Friday_Entity
import ru.den.omg.data.entity.Saturday_Entity
import ru.den.omg.data.entity.Thursday_Entity
import ru.den.omg.data.entity.Tuesday_Entity
import ru.den.omg.data.entity.Wednesday_Entity

@Database(
    entities = [
        Monday_Entity::class,
    Tuesday_Entity::class,
    Wednesday_Entity::class,
    Thursday_Entity::class,
    Friday_Entity::class,
    Saturday_Entity::class
               ],
    version = 2,
    autoMigrations = [
        AutoMigration(1, 2)
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