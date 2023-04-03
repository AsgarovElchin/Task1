package com.example.task1.data.database

import androidx.room.TypeConverter
import com.example.task1.models.Products
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverters {
    var gson = Gson()

    @TypeConverter
    fun productsToString(products: Products): String {
        return gson.toJson(products)
    }

    @TypeConverter
    fun stringToResult(data: String): Products {
        val listType = object : TypeToken<Products>() {}.type
        return gson.fromJson(data, listType)
    }

}