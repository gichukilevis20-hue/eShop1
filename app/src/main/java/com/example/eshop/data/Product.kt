package com.example.eshop.data

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val originalPrice: Double? = null,
    val category: String,
    val description: String,
    val imageUrl: String,
    val rating: Float,
    val reviewCount: Int,
    val inStock: Boolean = true
)
