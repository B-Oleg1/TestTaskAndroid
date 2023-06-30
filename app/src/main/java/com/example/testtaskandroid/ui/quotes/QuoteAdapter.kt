package com.example.testtaskandroid.ui.quotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskandroid.R

class QuoteAdapter(private val quotesInfo: Array<QuoteModel>) : RecyclerView.Adapter<QuoteAdapter.QuoteHolder>() {

    class QuoteHolder(item: View): RecyclerView.ViewHolder(item) {
        val textQuote: TextView = itemView.findViewById(R.id.textQuoteInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quote_item, parent, false)

        return QuoteHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteHolder, position: Int) {
        val quoteInfo = quotesInfo[position]
        holder.textQuote.text = "${quoteInfo.quote} © ${quoteInfo.author}"
    }

    override fun getItemCount(): Int {
        return quotesInfo.size
    }

}