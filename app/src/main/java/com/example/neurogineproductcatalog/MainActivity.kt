package com.example.neurogineproductcatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neurogineproductcatalog.data.ApiProduct
import com.example.neurogineproductcatalog.ui.components.CatalogHomeScreen
import com.example.neurogineproductcatalog.ui.components.WishlistScreen
import com.example.neurogineproductcatalog.ui.theme.NeurogineProductCatalogTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NeurogineProductCatalogTheme {
                var currentScreen by remember { mutableStateOf("Product") }
                val wishlist = remember { mutableStateListOf<ApiProduct>() }

                // Custom handling or standard layout with drawer arriving from top right / handled beautifully
                // Since Jetpack Compose ModalNavigationDrawer opens from left (start), we can customize it or layout it.
                // But wait! The requirement says "navigation drawer at top right of this app. thus, when user click on it, it will expand and show 2 button which is Product and Wishlist."
                // A very beautiful, reliable way to implement a "top right expanding drawer/menu" or navigation drawer exactly as requested is using a Box with an action bar menu or an overlay, or custom drawer that expands from the top right. Let's make an extremely elegant, interactive modal overlay drawer or use an icon button in a TopAppBar that toggles a right-anchored menu/panel, or standard DropdownMenu / sliding panel. Let's make a beautiful custom side drawer or dropdown container that slides/expands from the top right precisely as requested, so the user sees exactly "top right navigation drawer"!
                
                fun toggleWishlist(product: ApiProduct) {
                    if (wishlist.any { it.id == product.id }) {
                        wishlist.removeAll { it.id == product.id }
                    } else {
                        wishlist.add(product)
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        // Main Screen content
                        when (currentScreen) {
                            "Product" -> CatalogHomeScreen(
                                wishlist = wishlist,
                                onToggleWishlist = { toggleWishlist(it) }
                            )
                            "Wishlist" -> WishlistScreen(
                                wishlist = wishlist,
                                onToggleWishlist = { toggleWishlist(it) }
                            )
                        }

                        // Top Right Anchor for Menu/Drawer Button
                        var isDrawerExpanded by remember { mutableStateOf(false) }

                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(top = 48.dp, end = 16.dp)
                        ) {
                            IconButton(
                                onClick = { isDrawerExpanded = !isDrawerExpanded },
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = Color(0xFF4CAF50),
                                    contentColor = Color.White
                                ),
                                modifier = Modifier
                                    .size(48.dp)
                                    .align(Alignment.TopEnd)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Navigation Menu"
                                )
                            }

                            if (isDrawerExpanded) {
                                Card(
                                    modifier = Modifier
                                        .padding(top = 56.dp)
                                        .width(180.dp)
                                        .align(Alignment.TopEnd),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.White
                                    ),
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = 8.dp
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp)
                                    ) {
                                        Text(
                                            text = "Navigation",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Gray,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                        )
                                        
                                        HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                                        
                                        NavigationDrawerItem(
                                            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null, tint = if (currentScreen == "Product") Color(0xFF2E7D32) else Color.Gray) },
                                            label = { Text("Product", fontWeight = FontWeight.Bold, color = if (currentScreen == "Product") Color(0xFF2E7D32) else Color.Black) },
                                            selected = currentScreen == "Product",
                                            onClick = {
                                                currentScreen = "Product"
                                                isDrawerExpanded = false
                                            },
                                            colors = NavigationDrawerItemDefaults.colors(
                                                selectedContainerColor = Color(0xFFC8E6C9),
                                                unselectedContainerColor = Color.Transparent
                                            ),
                                            shape = RoundedCornerShape(12.dp),
                                            modifier = Modifier.padding(vertical = 2.dp)
                                        )

                                        NavigationDrawerItem(
                                            icon = { Icon(Icons.Default.Favorite, contentDescription = null, tint = if (currentScreen == "Wishlist") Color.Red else Color.Gray) },
                                            label = { Text("Wishlist", fontWeight = FontWeight.Bold, color = if (currentScreen == "Wishlist") Color.Red else Color.Black) },
                                            selected = currentScreen == "Wishlist",
                                            onClick = {
                                                currentScreen = "Wishlist"
                                                isDrawerExpanded = false
                                            },
                                            colors = NavigationDrawerItemDefaults.colors(
                                                selectedContainerColor = Color(0xFFFFCDD2),
                                                unselectedContainerColor = Color.Transparent
                                            ),
                                            shape = RoundedCornerShape(12.dp),
                                            modifier = Modifier.padding(vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
