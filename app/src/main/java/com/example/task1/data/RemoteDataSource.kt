package com.example.task1.data

import com.example.task1.data.network.ProductsApi
import com.example.task1.models.Product
import com.example.task1.models.Products
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val productsApi: ProductsApi
) {
    suspend fun getProducts():Response<Products>{
        return productsApi.getProducts()
    }
}