package com.example.task1.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task1.R
import com.example.task1.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {
    private lateinit var binding:FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater,container,false)
        return binding.root
    }
}

