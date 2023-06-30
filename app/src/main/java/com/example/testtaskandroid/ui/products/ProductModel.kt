package com.example.testtaskandroid.ui.products

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductModel(val title: String,
                        val price: Int,
                        val thumbnail: String,
                        val description: String,
                        val images: Array<String>,
                        @JsonIgnore var isDetailedInfo: Boolean)
