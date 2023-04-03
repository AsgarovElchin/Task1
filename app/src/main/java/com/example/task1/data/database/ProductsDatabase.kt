package com.example.task1.data.database

import androidx.room.Database
import com.example.task1.data.database.entities.ProductsEntity

@Database(
    entities = [ProductsEntity::class],
    version = 1
)
abstract class ProductsDatabase {

    abstract fun productsDao(): ProductsDao
}