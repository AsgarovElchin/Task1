package com.example.task1.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.task1.databinding.ProductsItemLayoutBinding
import com.example.task1.models.Product
import com.example.task1.models.Products

class ProductsViewHolder(val binding: ProductsItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product){
        binding.productImage.load(product.thumbnail)
        binding.productName.text = product.title
        binding.productDescription.text = product.description
    }

}