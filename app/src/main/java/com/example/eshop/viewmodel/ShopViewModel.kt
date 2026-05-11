package com.example.eshop.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.eshop.data.CartItem
import com.example.eshop.data.Product
import com.example.eshop.data.SampleData

class ShopViewModel : ViewModel() {

    val categories = SampleData.categories
    val products = SampleData.products

    val searchQuery = mutableStateOf("")
    val selectedCategory = mutableStateOf("All")

    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    val cartItemCount: Int
        get() = _cartItems.sumOf { it.quantity }

    val cartTotal: Double
        get() = _cartItems.sumOf { it.product.price * it.quantity }

    fun getFilteredProducts(): List<Product> {
        val query = searchQuery.value.trim()
        val category = selectedCategory.value
        return products.filter { product ->
            (category == "All" || product.category == category) &&
            (query.isEmpty() ||
             product.name.contains(query, ignoreCase = true) ||
             product.description.contains(query, ignoreCase = true) ||
             product.category.contains(query, ignoreCase = true))
        }
    }

    fun addToCart(product: Product, quantity: Int = 1) {
        val index = _cartItems.indexOfFirst { it.product.id == product.id }
        if (index >= 0) {
            _cartItems[index] = _cartItems[index].copy(
                quantity = _cartItems[index].quantity + quantity
            )
        } else {
            _cartItems.add(CartItem(product, quantity))
        }
    }

    fun updateQuantity(productId: Int, quantity: Int) {
        if (quantity <= 0) {
            removeFromCart(productId)
            return
        }
        val index = _cartItems.indexOfFirst { it.product.id == productId }
        if (index >= 0) {
            _cartItems[index] = _cartItems[index].copy(quantity = quantity)
        }
    }

    fun removeFromCart(productId: Int) {
        _cartItems.removeAll { it.product.id == productId }
    }

    fun clearCart() {
        _cartItems.clear()
    }
}
