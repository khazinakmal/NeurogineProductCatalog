package com.example.neurogineproductcatalog.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

data class ApiProduct(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val category: String,
    val thumbnail: String,
    val rating: Double
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
                    productsList.add(
                        ApiProduct(
                            id = p.getInt("id"),
                            title = p.getString("title"),
                            description = p.getString("description"),
                            price = p.getDouble("price"),
                            category = p.getString("category"),
                            thumbnail = p.optString("thumbnail", ""),
                            rating = p.optDouble("rating", 0.0)
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
