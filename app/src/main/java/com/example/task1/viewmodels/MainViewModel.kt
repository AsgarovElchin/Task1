package com.example.task1.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.task1.data.Repository
import com.example.task1.data.database.entities.ProductsEntity
import com.example.task1.models.Products
import com.example.task1.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application

) : AndroidViewModel(application) {

    val readProducts: LiveData<List<ProductsEntity>> = repository.local.readProducts().asLiveData()
    private var productsResponse: MutableLiveData<NetworkResult<Products>> = MutableLiveData()

    fun searchDatabase(searchQuery: String): LiveData<List<ProductsEntity>> {
        return repository.local.searchDatabase(searchQuery)
    }

    private fun insertProducts(productsEntity: ProductsEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertProducts(productsEntity)
        }

    fun getProducts() = viewModelScope.launch {
        getProductsSafeCall()
    }

    private suspend fun getProductsSafeCall() {
        productsResponse.value = NetworkResult.Loading()
        val response = repository.remote.getProducts()
        productsResponse.value = handleProductsResponse(response)


    }

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
                val productsEntity = products?.let { ProductsEntity(it) }
                if (productsEntity != null) {
                    insertProducts(productsEntity)
                }
                return NetworkResult.Success(products!!)

            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

}







