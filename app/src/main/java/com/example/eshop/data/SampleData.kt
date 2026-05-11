package com.example.eshop.data

object SampleData {

    val categories = listOf("All", "Electronics", "Clothing", "Books", "Sports", "Home")

    val products = listOf(
        // Electronics
        Product(
            id = 1,
            name = "Wireless Noise-Cancelling Headphones",
            price = 79.99,
            originalPrice = 129.99,
            category = "Electronics",
            description = "Premium wireless headphones with active noise cancellation. Enjoy up to 30 hours of battery life, crystal-clear sound, and a comfortable over-ear design. Perfect for music lovers and remote workers.",
            imageUrl = "https://picsum.photos/seed/headphones/400/400",
            rating = 4.5f,
            reviewCount = 2841
        ),
        Product(
            id = 2,
            name = "Smart Watch Pro",
            price = 199.99,
            category = "Electronics",
            description = "Track your health and stay connected with this feature-packed smartwatch. Includes heart rate monitoring, GPS, sleep tracking, and 50+ workout modes. Water-resistant up to 50 meters.",
            imageUrl = "https://picsum.photos/seed/smartwatch/400/400",
            rating = 4.3f,
            reviewCount = 1567
        ),
        Product(
            id = 3,
            name = "Portable Bluetooth Speaker",
            price = 49.99,
            originalPrice = 69.99,
            category = "Electronics",
            description = "Compact yet powerful Bluetooth speaker with 360° surround sound. IPX7 waterproof rating, 12-hour playtime, and built-in microphone for hands-free calls. Connects up to 33 feet away.",
            imageUrl = "https://picsum.photos/seed/btspeaker/400/400",
            rating = 4.4f,
            reviewCount = 983
        ),
        Product(
            id = 4,
            name = "Ergonomic Laptop Stand",
            price = 34.99,
            category = "Electronics",
            description = "Adjustable aluminum laptop stand that improves posture and keeps your device cool. Compatible with 10–17 inch laptops. Foldable design for home and office use.",
            imageUrl = "https://picsum.photos/seed/laptopstand/400/400",
            rating = 4.6f,
            reviewCount = 3214
        ),
        // Clothing
        Product(
            id = 5,
            name = "Premium Cotton T-Shirt",
            price = 24.99,
            category = "Clothing",
            description = "Crafted from 100% organic cotton, this classic t-shirt offers unmatched comfort and durability. Available in multiple colors with a relaxed fit suitable for all occasions.",
            imageUrl = "https://picsum.photos/seed/cottonshirt/400/400",
            rating = 4.2f,
            reviewCount = 5621
        ),
        Product(
            id = 6,
            name = "Slim Fit Denim Jeans",
            price = 59.99,
            originalPrice = 89.99,
            category = "Clothing",
            description = "Modern slim-fit jeans made from stretch denim for maximum comfort. Features five-pocket styling, a mid-rise waist, and tapered leg. Machine washable and built to last.",
            imageUrl = "https://picsum.photos/seed/denimjeans/400/400",
            rating = 4.1f,
            reviewCount = 2198
        ),
        Product(
            id = 7,
            name = "Waterproof Winter Jacket",
            price = 149.99,
            category = "Clothing",
            description = "Stay warm and dry with this insulated winter jacket. Features a waterproof shell, fleece lining, multiple pockets, and adjustable hood. Suitable for temperatures down to -10°C.",
            imageUrl = "https://picsum.photos/seed/winterjacket/400/400",
            rating = 4.7f,
            reviewCount = 876
        ),
        Product(
            id = 8,
            name = "Athletic Running Shoes",
            price = 89.99,
            category = "Clothing",
            description = "Lightweight and responsive running shoes with breathable mesh upper. Features cushioned insole, durable rubber outsole, and reflective details for low-light visibility.",
            imageUrl = "https://picsum.photos/seed/runningshoes/400/400",
            rating = 4.4f,
            reviewCount = 3457
        ),
        // Books
        Product(
            id = 9,
            name = "The Art of Clean Code",
            price = 19.99,
            category = "Books",
            description = "A comprehensive guide to writing maintainable, readable, and efficient code. Learn proven best practices from industry experts with real-world examples and exercises.",
            imageUrl = "https://picsum.photos/seed/cleancode/400/400",
            rating = 4.8f,
            reviewCount = 7832
        ),
        Product(
            id = 10,
            name = "Photography Masterclass",
            price = 29.99,
            category = "Books",
            description = "From beginner to professional, this comprehensive photography guide covers composition, lighting, post-processing, and business tips. Includes 500+ example photos.",
            imageUrl = "https://picsum.photos/seed/photobook/400/400",
            rating = 4.5f,
            reviewCount = 2341
        ),
        Product(
            id = 11,
            name = "Design Thinking Handbook",
            price = 24.99,
            category = "Books",
            description = "Learn the human-centered approach to innovation used by the world's leading companies. Packed with frameworks, case studies, and actionable strategies you can apply immediately.",
            imageUrl = "https://picsum.photos/seed/designbook/400/400",
            rating = 4.3f,
            reviewCount = 1654
        ),
        Product(
            id = 12,
            name = "Business Analytics Guide",
            price = 34.99,
            category = "Books",
            description = "Master data-driven decision making with this practical guide to business analytics. Covers statistics, visualization, predictive modeling, and real-world applications.",
            imageUrl = "https://picsum.photos/seed/analyticsbook/400/400",
            rating = 4.2f,
            reviewCount = 943
        ),
        // Sports
        Product(
            id = 13,
            name = "Premium Yoga Mat",
            price = 39.99,
            category = "Sports",
            description = "Non-slip, eco-friendly yoga mat with alignment guides. 6mm thick for optimal joint protection. Made from natural rubber, free from PVC and harmful chemicals. Includes carry strap.",
            imageUrl = "https://picsum.photos/seed/yogamat/400/400",
            rating = 4.6f,
            reviewCount = 4521
        ),
        Product(
            id = 14,
            name = "Resistance Bands Set (5-Pack)",
            price = 24.99,
            category = "Sports",
            description = "Complete set of 5 resistance bands ranging from 10 to 50 lbs. Perfect for strength training, physical therapy, and stretching. Made from heavy-duty latex with anti-slip handles.",
            imageUrl = "https://picsum.photos/seed/resistbands/400/400",
            rating = 4.4f,
            reviewCount = 6782
        ),
        Product(
            id = 15,
            name = "Insulated Water Bottle",
            price = 29.99,
            originalPrice = 39.99,
            category = "Sports",
            description = "Triple-insulated stainless steel bottle keeps drinks cold for 24 hours and hot for 12 hours. BPA-free, leak-proof lid, and wide mouth for easy cleaning. Fits most cup holders.",
            imageUrl = "https://picsum.photos/seed/waterbottle/400/400",
            rating = 4.7f,
            reviewCount = 9234
        ),
        Product(
            id = 16,
            name = "Professional Tennis Racket",
            price = 69.99,
            category = "Sports",
            description = "Lightweight graphite tennis racket with a large sweet spot for enhanced power and control. Pre-strung with high-tension strings. Suitable for intermediate to advanced players.",
            imageUrl = "https://picsum.photos/seed/tennisracket/400/400",
            rating = 4.3f,
            reviewCount = 567
        ),
        // Home
        Product(
            id = 17,
            name = "Luxury Scented Candle Set",
            price = 34.99,
            category = "Home",
            description = "Set of 3 hand-poured soy wax candles in elegant glass jars. Fragrances include lavender, vanilla, and eucalyptus. 40+ hour burn time per candle. Perfect as a gift or home decor.",
            imageUrl = "https://picsum.photos/seed/candleset/400/400",
            rating = 4.8f,
            reviewCount = 3218
        ),
        Product(
            id = 18,
            name = "Velvet Throw Pillow Cover",
            price = 19.99,
            category = "Home",
            description = "Luxurious velvet pillow cover in a rich jewel tone. Hidden zipper closure for easy removal and machine washing. Fits standard 18x18 inch pillow inserts. Adds elegance to any room.",
            imageUrl = "https://picsum.photos/seed/throwpillow/400/400",
            rating = 4.5f,
            reviewCount = 1876
        ),
        Product(
            id = 19,
            name = "Smart Pour-Over Coffee Maker",
            price = 89.99,
            originalPrice = 119.99,
            category = "Home",
            description = "Precision pour-over coffee maker with built-in scale and timer. Brews the perfect cup every time with adjustable flow rate and temperature control. Includes reusable mesh filter.",
            imageUrl = "https://picsum.photos/seed/coffeemaker/400/400",
            rating = 4.6f,
            reviewCount = 2134
        ),
        Product(
            id = 20,
            name = "Minimalist Wall Clock",
            price = 44.99,
            category = "Home",
            description = "Sleek and silent wall clock with a Nordic minimalist design. 12-inch diameter with clear numerals and a sweep second hand for near-silent operation. Requires one AA battery.",
            imageUrl = "https://picsum.photos/seed/wallclock/400/400",
            rating = 4.4f,
            reviewCount = 892
        )
    )
}
