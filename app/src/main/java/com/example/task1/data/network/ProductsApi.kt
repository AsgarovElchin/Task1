package com.example.task1.data.network

import com.example.task1.models.Products
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {

    @GET("/products")
    suspend fun getProducts():Response<Products>
}