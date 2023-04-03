package com.example.task1.ui.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task1.R
import com.example.task1.adapters.ProductsAdapter
import com.example.task1.databinding.FragmentProductsBinding
import com.example.task1.models.Products
import com.example.task1.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentProductsBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val mAdapter by lazy { ProductsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.syncBtn.setOnClickListener {
            mainViewModel.getProducts()
            binding.syncBtn.visibility = View.INVISIBLE

        }

        mainViewModel.readProducts.observe(viewLifecycleOwner,{data->
            if(data.isEmpty()){
                binding.syncBtn.visibility = View.VISIBLE
            }
            else{
                binding.syncBtn.visibility = View.INVISIBLE
            }
        })


        setupMenu()
        setupRecyclerView()
        mainViewModel.readProducts.observe(viewLifecycleOwner, { database ->
            if (database.isNotEmpty()) {
                mAdapter.setData(database[0].products)
            }
        })

    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {

            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
                val search = menu.findItem(R.id.menu_search)
                val searchView = search.actionView as? androidx.appcompat.widget.SearchView
                searchView?.isSubmitButtonEnabled = true
                searchView?.setOnQueryTextListener(this@ProductsFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }



    private fun setupRecyclerView() {
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchThroughDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchThroughDatabase(query)
        }
        return true
    }

    private fun searchThroughDatabase(query: String) {
        mainViewModel.readProducts.observe(viewLifecycleOwner, { data ->
            data?.let {
                val items = it.map { it.products }
                val products = items.flatMap { it.products } // Flatten the list of products
                val filteredProducts = products.filter { it.title.contains(query) or it.description.contains(query)} // Filter by search query
                mAdapter.setData(Products(0, filteredProducts, 0, filteredProducts.size))
            }
        })

    }
}

