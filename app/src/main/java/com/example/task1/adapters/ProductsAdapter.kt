package com.example.task1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.adapters.viewholders.ProductsViewHolder
import com.example.task1.databinding.ProductsItemLayoutBinding
import com.example.task1.models.Product
import com.example.task1.models.Products
import com.example.task1.utils.ProductsDiffUtil

class ProductsAdapter : RecyclerView.Adapter<ProductsViewHolder>() {
    private var product = emptyList<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductsItemLayoutBinding.inflate(layoutInflater, parent, false)
        return ProductsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return product.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val currentResult = product[position]
        holder.bind(currentResult)
    }

    fun setData(newData: Products) {
        val recipesDiffUtil = ProductsDiffUtil(product, newData.products)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        product = newData.products
        diffUtilResult.dispatchUpdatesTo(this)


    }
}