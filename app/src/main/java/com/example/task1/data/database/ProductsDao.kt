package com.example.task1.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.task1.data.database.entities.ProductsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductsDao {

    @Query("SELECT * FROM products_table")
    fun readProducts(): Flow<List<ProductsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(productsEntity: ProductsEntity)

    @Query("SELECT * FROM products_table WHERE products LIKE '%' || :title || '%'")
    fun searchDatabase(title: String): LiveData<List<ProductsEntity>>
}