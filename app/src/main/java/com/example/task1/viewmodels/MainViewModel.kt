package com.example.task1.viewmodels

import com.example.task1.data.Repository
import com.example.task1.models.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) {


}