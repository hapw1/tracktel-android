package com.example.track_tel_android.data.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson

object ListConverter {

    @TypeConverter
    @JvmStatic
    fun toList(string: String) = Gson().fromJson(string, Array<Long>::class.java).toList()

    @TypeConverter
    @JvmStatic
    fun toString(list: List<Long>?) : String = Gson().toJson(list)
}