package com.example.testtaskandroid.ui.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskandroid.R
import com.squareup.picasso.Picasso

class ProductAdapter(private val productsInfo: Array<ProductModel>) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    class ProductHolder(item: View): RecyclerView.ViewHolder(item) {
        val cardRelativeLayout: RelativeLayout = itemView.findViewById(R.id.cardRelativeLayout)
        val productCardImage: ImageView = itemView.findViewById(R.id.productCardImage)
        val productCardTitle: TextView = itemView.findViewById(R.id.productCardTitle)
        val productCardDescr: TextView = itemView.findViewById(R.id.productCardDescr)
        val productCardPrice: TextView = itemView.findViewById(R.id.productCardPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)

        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val productInfo = productsInfo[position]

        holder.productCardTitle.text = productInfo.title
        holder.productCardDescr.text = productInfo.description
        holder.productCardPrice.text = "${productInfo.price} ₽"

        holder.productCardDescr.isInvisible = !productInfo.isDetailedInfo

        val imageUrl = if (productInfo.isDetailedInfo && productInfo.images.isNotEmpty()) {
            productInfo.images[(0 until productInfo.images.size).random()]
        } else {
            productInfo.thumbnail
        }
        Picasso.get().load(imageUrl).into(holder.productCardImage)

        holder.cardRelativeLayout.setOnClickListener {
            productInfo.isDetailedInfo = !productInfo.isDetailedInfo
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return productsInfo.size
    }

}