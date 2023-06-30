package com.example.testtaskandroid.ui.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testtaskandroid.databinding.FragmentQuotesBinding
import com.example.testtaskandroid.fetchData.FetchData

class QuotesFragment : Fragment() {

    private var _binding: FragmentQuotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuotesBinding.inflate(inflater, container, false)

        FetchData().fetchQuotesData(::updateProductsInfo).start()

        return binding.root
    }

    private fun updateProductsInfo(quoteInfo: Array<QuoteModel>) {
        activity?.runOnUiThread {
            val recyclerView = binding.quotesRecyclerView
            recyclerView.adapter = QuoteAdapter(quoteInfo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}