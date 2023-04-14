package com.lttrung.dormitory.utils

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun stringToList(value: String): List<String> {
        return Gson().fromJson(value, Array<String>::class.java).toList()
    }

    @TypeConverter
    fun listToString(value: List<String>): String {
        return Gson().toJson(value)
    }
}