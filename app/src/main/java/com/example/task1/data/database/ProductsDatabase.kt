package com.example.task1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.task1.data.database.entities.ProductsEntity

@Database(
    entities = [ProductsEntity::class],
    version = 1
)
@TypeConverters(com.example.task1.data.database.TypeConverters::class)
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun productsDao(): ProductsDao
}