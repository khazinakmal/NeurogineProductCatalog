package com.example.neurogineproductcatalog.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

data class Review(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String
)

data class ApiProduct(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val category: String,
    val thumbnail: String,
    val rating: Double,
    val discountPercentage: Double,
    val stock: Int,
    val sku: String,
    val warrantyInformation: String,
    val shippingInformation: String,
    val availabilityStatus: String,
    val minimumOrderQuantity: Int,
    val reviews: List<Review> = emptyList()
)

object DummyJsonService {
    
    suspend fun fetchProducts(limit: Int = 20, skip: Int = 0): List<ApiProduct> = withContext(Dispatchers.IO) {
        val urlString = "https://dummyjson.com/products?limit=$limit&skip=$skip"
        val productsList = mutableListOf<ApiProduct>()
        
        try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()
                
                val jsonObject = JSONObject(response.toString())
                val jsonArray = jsonObject.getJSONArray("products")
                
                for (i in 0 until jsonArray.length()) {
                    val p = jsonArray.getJSONObject(i)
                    val reviewsArray = p.optJSONArray("reviews")
                    val reviews = mutableListOf<Review>()
                    if (reviewsArray != null) {
                        for (j in 0 until reviewsArray.length()) {
                            val r = reviewsArray.getJSONObject(j)
                            reviews.add(
                                Review(
                                    rating = r.getInt("rating"),
                                    comment = r.getString("comment"),
                                    date = r.getString("date"),
                                    reviewerName = r.getString("reviewerName"),
                                    reviewerEmail = r.getString("reviewerEmail")
                                )
                            )
                        }
                    }

                    productsList.add(
                        ApiProduct(
                            id = p.getInt("id"),
                            title = p.getString("title"),
                            description = p.getString("description"),
                            price = p.getDouble("price"),
                            category = p.getString("category"),
                            thumbnail = p.optString("thumbnail", ""),
                            rating = p.optDouble("rating", 0.0),
                            discountPercentage = p.optDouble("discountPercentage", 0.0),
                            stock = p.optInt("stock", 0),
                            sku = p.optString("sku", "N/A"),
                            warrantyInformation = p.optString("warrantyInformation", "N/A"),
                            shippingInformation = p.optString("shippingInformation", "N/A"),
                            availabilityStatus = p.optString("availabilityStatus", "Unknown"),
                            minimumOrderQuantity = p.optInt("minimumOrderQuantity", 1),
                            reviews = reviews
                        )
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        return@withContext productsList
    }
}
