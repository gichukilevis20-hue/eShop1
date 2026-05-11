package com.example.eshop.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.eshop.viewmodel.ShopViewModel
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    viewModel: ShopViewModel,
    onBack: () -> Unit,
    onOrderPlaced: (String) -> Unit
) {
    // Personal info
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    // Address
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var zipCode by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    // Payment
    var selectedPayment by remember { mutableStateOf("Credit Card") }
    var cardNumber by remember { mutableStateOf("") }
    var cardName by remember { mutableStateOf("") }
    var cardExpiry by remember { mutableStateOf("") }
    var cardCvv by remember { mutableStateOf("") }
    // Validation
    var showErrors by remember { mutableStateOf(false) }

    val paymentMethods = listOf("Credit Card", "PayPal", "Cash on Delivery")
    val cartItems = viewModel.cartItems
    val cartTotal = viewModel.cartTotal
    val shipping = if (cartTotal >= 50.0) 0.0 else 9.99
    val total = cartTotal + shipping

    fun isFormValid(): Boolean {
        if (fullName.isBlank() || email.isBlank() || phone.isBlank()) return false
        if (!email.contains("@") || !email.contains(".")) return false
        if (address.isBlank() || city.isBlank() || zipCode.isBlank() || country.isBlank()) return false
        if (selectedPayment == "Credit Card") {
            if (cardNumber.replace(" ", "").length < 16) return false
            if (cardName.isBlank()) return false
            if (cardExpiry.length < 5) return false
            if (cardCvv.length < 3) return false
        }
        return true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkout", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // ── Personal Information ──────────────────────────────────────
            item {
                SectionHeader(icon = Icons.Default.Person, title = "Personal Information")
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Full Name *") },
                    leadingIcon = { Icon(Icons.Default.Person, null) },
                    isError = showErrors && fullName.isBlank(),
                    supportingText = if (showErrors && fullName.isBlank()) {
                        { Text("Name is required") }
                    } else null,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email Address *") },
                    leadingIcon = { Icon(Icons.Default.Email, null) },
                    isError = showErrors && (email.isBlank() || !email.contains("@")),
                    supportingText = if (showErrors && (email.isBlank() || !email.contains("@"))) {
                        { Text("Valid email required") }
                    } else null,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = phone,
                    onValueChange = { if (it.length <= 15) phone = it },
                    label = { Text("Phone Number *") },
                    leadingIcon = { Icon(Icons.Default.Phone, null) },
                    isError = showErrors && phone.isBlank(),
                    supportingText = if (showErrors && phone.isBlank()) {
                        { Text("Phone is required") }
                    } else null,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    )
                )
            }

            // ── Delivery Address ──────────────────────────────────────────
            item {
                HorizontalDivider()
                Spacer(modifier = Modifier.height(4.dp))
                SectionHeader(icon = Icons.Default.Home, title = "Delivery Address")
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Street Address *") },
                    leadingIcon = { Icon(Icons.Default.LocationOn, null) },
                    isError = showErrors && address.isBlank(),
                    supportingText = if (showErrors && address.isBlank()) {
                        { Text("Address is required") }
                    } else null,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
                        label = { Text("City *") },
                        isError = showErrors && city.isBlank(),
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )
                    OutlinedTextField(
                        value = state,
                        onValueChange = { state = it },
                        label = { Text("State") },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = zipCode,
                        onValueChange = { if (it.length <= 10) zipCode = it },
                        label = { Text("ZIP Code *") },
                        isError = showErrors && zipCode.isBlank(),
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )
                    OutlinedTextField(
                        value = country,
                        onValueChange = { country = it },
                        label = { Text("Country *") },
                        isError = showErrors && country.isBlank(),
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                    )
                }
            }

            // ── Payment Method ────────────────────────────────────────────
            item {
                HorizontalDivider()
                Spacer(modifier = Modifier.height(4.dp))
                SectionHeader(icon = Icons.Default.Payment, title = "Payment Method")
                Spacer(modifier = Modifier.height(8.dp))

                paymentMethods.forEach { method ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedPayment == method,
                            onClick = { selectedPayment = method }
                        )
                        Text(
                            text = method,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }

                if (selectedPayment == "Credit Card") {
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = cardNumber,
                                onValueChange = { input ->
                                    val digits = input.replace(" ", "").filter { it.isDigit() }
                                    if (digits.length <= 16) {
                                        cardNumber = digits.chunked(4).joinToString(" ")
                                    }
                                },
                                label = { Text("Card Number *") },
                                leadingIcon = { Icon(Icons.Default.CreditCard, null) },
                                placeholder = { Text("1234 5678 9012 3456") },
                                isError = showErrors && cardNumber.replace(" ", "").length < 16,
                                supportingText = if (showErrors && cardNumber.replace(" ", "").length < 16) {
                                    { Text("Enter 16-digit card number") }
                                } else null,
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Next
                                )
                            )
                            OutlinedTextField(
                                value = cardName,
                                onValueChange = { cardName = it },
                                label = { Text("Cardholder Name *") },
                                isError = showErrors && cardName.isBlank(),
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                OutlinedTextField(
                                    value = cardExpiry,
                                    onValueChange = { input ->
                                        val digits = input.filter { it.isDigit() }
                                        cardExpiry = when {
                                            digits.length >= 2 -> "${digits.substring(0, 2)}/${digits.substring(2).take(2)}"
                                            else -> digits
                                        }
                                    },
                                    label = { Text("MM/YY *") },
                                    placeholder = { Text("MM/YY") },
                                    isError = showErrors && cardExpiry.length < 5,
                                    modifier = Modifier.weight(1f),
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Next
                                    )
                                )
                                OutlinedTextField(
                                    value = cardCvv,
                                    onValueChange = {
                                        if (it.length <= 4 && it.all { c -> c.isDigit() }) cardCvv = it
                                    },
                                    label = { Text("CVV *") },
                                    placeholder = { Text("123") },
                                    isError = showErrors && cardCvv.length < 3,
                                    modifier = Modifier.weight(1f),
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.NumberPassword,
                                        imeAction = ImeAction.Done
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // ── Order Summary ─────────────────────────────────────────────
            item {
                HorizontalDivider()
                Spacer(modifier = Modifier.height(4.dp))
                SectionHeader(icon = Icons.Default.Receipt, title = "Order Summary")
                Spacer(modifier = Modifier.height(8.dp))

                cartItems.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${item.product.name} ×${item.quantity}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "$${String.format("%.2f", item.product.price * item.quantity)}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Shipping",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        if (shipping == 0.0) "FREE" else "$${String.format("%.2f", shipping)}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Total",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        "$${String.format("%.2f", total)}",
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // ── Place Order Button ────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(4.dp))
                Button(
                    onClick = {
                        if (isFormValid()) {
                            val orderNumber = "ORD-${Random.nextInt(100000, 999999)}"
                            viewModel.clearCart()
                            onOrderPlaced(orderNumber)
                        } else {
                            showErrors = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Place Order — $${String.format("%.2f", total)}",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun SectionHeader(icon: ImageVector, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
