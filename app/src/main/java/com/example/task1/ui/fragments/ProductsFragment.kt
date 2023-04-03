package com.example.task1.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task1.R
import com.example.task1.adapters.ProductsAdapter
import com.example.task1.databinding.FragmentProductsBinding
import com.example.task1.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private lateinit var binding:FragmentProductsBinding
    private  val mainViewModel: MainViewModel by viewModels()
    private val mAdapter by lazy { ProductsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.syncBtn.setOnClickListener {
            mainViewModel.getProducts()
            binding.syncBtn.visibility = View.INVISIBLE
        }
        setupRecyclerView()
        mainViewModel.readProducts.observe(viewLifecycleOwner,{database->
            if(database.isNotEmpty()){
                mAdapter.setData(database[0].products)
            }
        })

    }


    private fun setupRecyclerView() {
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
    }
}

