package ru.den.omg

import android.app.Application
import ru.den.omg.data.Mine_Data24

class App : Application() {
    val data by lazy { Mine_Data24.createDatabase(this) }
}