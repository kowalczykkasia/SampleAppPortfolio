package com.example.sampleapp.db

import androidx.room.TypeConverter
import com.example.sampleapp.models.Item
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun itemToJson(list: List<Item>?): String? = list?.let { Gson().toJson(it) }

    @TypeConverter
    fun jsonToItem(value: String?): List<Item>? = value?.let {
        Gson().fromJson(it, Array<Item>::class.java) as Array<Item>
    }?.toList()
}