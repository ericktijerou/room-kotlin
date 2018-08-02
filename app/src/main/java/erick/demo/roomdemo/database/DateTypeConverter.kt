package erick.demo.roomdemo.database

import android.arch.persistence.room.TypeConverter
import java.util.*


object DateTypeConverter {

    @TypeConverter
    fun toDate(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun toLong(value: Date?): Long? {
        return if (value == null) null else value.time
    }
}