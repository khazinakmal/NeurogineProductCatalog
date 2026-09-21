**PRODUCT CATALOG APP**

A simple Android Product Catalog application built with Kotlin and Jetpack Compose using the
DummyJSON API. The app allows users to browse products, load more products through pagination,
search products, and view detailed product information.

**TECH STACK USED FOR THIS CATALOG APP**

- Kotlin
- Jetpack Compose
- Retrofit
- Coil

**ARCHITECTURE OF THIS CATALOG APP**

I used MVVM (Model-View-ViewModel) architecture pattern for this Catalog App.
MVVM was chosen because it provides clear separation between UI and business logic,
making the application easier to maintain and extend.

UI → ViewModel → Repository → API

**SUCCESS FEATURES OF THIS CATALOG APP IN 3 HOURS DEVELOPMENT**

1. Product List Screen
    - Product title
    - Product thumbnail
    - Product price

2. Pagination
    - Load more products when reaching the bottom of the list
    - Implemented using DummyJSON using skip and limit parameters

3. Product Detail Screen
    - Product description
    - Product rating
    - Review Tab

4. Search Functionality
    - Search products using DummyJSON search endpoint

**FEATURES NOT COMPLETED WITHIN THE 3-HOUR TIMEBOX**

1. State Handling
    - Error State with Retry Button
    - Empty State

**ADDITIONAL FEATURES**

1. Navigation Drawer (Additional Feature)
2. Wishlist Screen (Additional Feature)

**COMMIT HISTORY FOR THIS CATALOG APP**

- feat: initialize Android project
- feat: implement product list
- feat: implement pagination
- feat: implement product detail
- feat: implement search bar
- feat: implement addition features