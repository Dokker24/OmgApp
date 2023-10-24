package ru.den.omg.time

class TimeForDatabase {
    companion object {
        fun timeAfterIntHour(time: String): Int {
            return time.split(':')[0].toInt()
        }
        fun timeAfterIntMinute(time: String): Int {
            return time.split(':')[0].toInt()
        }
    }
}