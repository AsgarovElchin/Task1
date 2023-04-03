package com.example.task1.data

import androidx.lifecycle.LiveData
import com.example.task1.data.database.ProductsDao
import com.example.task1.data.database.entities.ProductsEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val productsDao: ProductsDao
) {

    fun searchDatabase(searchQuery: String): LiveData<List<ProductsEntity>>{
        return productsDao.searchDatabase(searchQuery)
    }

    fun readProducts():kotlinx.coroutines.flow.Flow<List<ProductsEntity>>{
        return productsDao.readProducts()
    }

    suspend fun insertProducts(productsEntity: ProductsEntity){
        return productsDao.insertProducts(productsEntity)
    }


}