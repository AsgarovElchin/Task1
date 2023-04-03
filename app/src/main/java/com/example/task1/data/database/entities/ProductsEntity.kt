package com.example.task1.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.task1.models.Products
import com.example.task1.utils.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class ProductsEntity(
    var products: Products
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

}