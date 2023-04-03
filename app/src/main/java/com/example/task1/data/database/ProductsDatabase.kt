package com.example.task1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.task1.data.database.entities.ProductsEntity

@Database(
    entities = [ProductsEntity::class],
    version = 1
)
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun productsDao(): ProductsDao
}