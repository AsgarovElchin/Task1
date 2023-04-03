package com.example.task1.models

data class Products(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)