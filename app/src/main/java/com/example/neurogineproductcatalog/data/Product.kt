package com.example.neurogineproductcatalog.data

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val category: String,
    val emoji: String,
    val rating: Double
)

val sampleProducts = listOf(
    Product(
        id = 1,
        name = "Quantum Portal Gun",
        description = "Travel to any parallel universe in seconds! Safe for dimensions C-137 and above.",
        price = "999.99 Credits",
        category = "Gadgets",
        emoji = "🔫",
        rating = 4.9
    ),
    Product(
        id = 2,
        name = "Anti-Gravity Hoverboard",
        description = "Glide smoothly over any terrain, whether it's water, rocks, or cosmic stardust.",
        price = "249.50 Credits",
        category = "Vehicles",
        emoji = "🛹",
        rating = 4.7
    ),
    Product(
        id = 3,
        name = "Cosmic Invisibility Cloak",
        description = "Woven from dark matter. Renders you completely invisible to alien lifeforms and radar.",
        price = "450.00 Credits",
        category = "Wearables",
        emoji = "🧥",
        rating = 4.8
    ),
    Product(
        id = 4,
        name = "Universal Babel Fish",
        description = "Slip this little buddy into your ear to instantly understand any language spoken in the multiverse.",
        price = "75.00 Credits",
        category = "BioTech",
        emoji = "🐟",
        rating = 5.0
    ),
    Product(
        id = 5,
        name = "Schrödinger's Surprise Box",
        description = "Contains an item that is simultaneously amazing and legendary until you open it.",
        price = "120.00 Credits",
        category = "Gadgets",
        emoji = "📦",
        rating = 4.5
    ),
    Product(
        id = 6,
        name = "Hyperdrive Chrono-Watch",
        description = "Rewind time by exactly 10 seconds. Perfect for fixing awkward handshakes or spilled coffee.",
        price = "620.00 Credits",
        category = "Wearables",
        emoji = "⌚",
        rating = 4.6
    )
)
