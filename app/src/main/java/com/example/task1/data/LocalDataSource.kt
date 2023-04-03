package com.example.task1.data

import com.example.task1.data.database.entities.ProductsEntity
import java.util.concurrent.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val productsDao: ProductsDao
) {

    fun readProducts():kotlinx.coroutines.flow.Flow<List<ProductsEntity>>{
        return productsDao.readProducts()
    }

    suspend fun insertProducts(productsEntity: ProductsEntity){
        return productsDao.insertProducts(productsEntity)
    }
}