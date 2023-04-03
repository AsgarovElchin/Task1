package com.example.task1.viewmodels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.task1.data.Repository
import com.example.task1.models.Products
import com.example.task1.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository

)


    private fun handleProductsResponse(response: Response<Products>): NetworkResult<Products>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }
            response.body()!!.products.isNullOrEmpty() -> {
                return NetworkResult.Error("Products not found.")
            }
            response.isSuccessful -> {
                val products = response.body()
                return NetworkResult.Success(products!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }


