# eShop1

A shopping app built with Kotlin and Jetpack Compose. Single-activity, MVVM layout.

## What it does

- Browse products in a grid, filtered by category
- Open a product page with rating, price, and stock status
- Add items to a cart
- Check out and see an order confirmation

## Tech

- Kotlin
- Jetpack Compose (Material 3)
- ViewModel + StateFlow for app state
- Navigation Compose for moving between screens
- Sample product data baked in (`SampleData.kt`) — no backend needed

## Structure

```
app/src/main/java/com/example/eshop/
├── MainActivity.kt          single activity
├── data/                    Product, CartItem, SampleData
├── navigation/              AppNavigation (routes)
├── screens/                 Home, ProductDetail, Cart, Checkout, OrderConfirmation
├── ui/theme/                colors, type, theme
└── viewmodel/               ShopViewModel
```

## Run it

Open the project in Android Studio and run on an emulator or device. No API keys or backend setup required.

## Note

This repo is the real version of the project. An older, empty `eShop` repo was deleted.
