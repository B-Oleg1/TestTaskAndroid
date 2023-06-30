package com.example.testtaskandroid.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testtaskandroid.databinding.FragmentProductsBinding
import com.example.testtaskandroid.fetchData.FetchData

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)

        FetchData().fetchProductsData(::updateProductsInfo).start()

        return binding.root
    }

    private fun updateProductsInfo(productsInfo: Array<ProductModel>) {
        activity?.runOnUiThread {
            val recyclerView = binding.productRecyclerView
            recyclerView.adapter = ProductAdapter(productsInfo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}