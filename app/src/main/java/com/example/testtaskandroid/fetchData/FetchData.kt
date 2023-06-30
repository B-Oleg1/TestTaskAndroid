package com.example.testtaskandroid.fetchData

import com.example.testtaskandroid.ui.products.ProductModel
import com.example.testtaskandroid.ui.quotes.QuoteModel
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class FetchData {
    private val _baseUrl = "https://dummyjson.com"

    fun checkServerAvailability(callback: (isAvailable: Boolean) -> Unit): Thread {
        return Thread {
            var isAvailable = false

            try {
                val url = URL("${_baseUrl}/my-test-route")
                val connection = url.openConnection() as HttpsURLConnection
                isAvailable = connection.responseCode != 500
            } catch (e: Exception) {
                // TODO: some error
            } finally {
                callback(isAvailable)
            }
        }
    }

    fun fetchQuotesData(callback: (quoteInfo: Array<QuoteModel>) -> Unit): Thread {
        return Thread {
            try {
                val url = URL("${_baseUrl}/quotes")
                val connection = url.openConnection() as HttpsURLConnection

                if (connection.responseCode == 200) {
                    val inputSystem = connection.inputStream
                    val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")

                    val mapper = jacksonObjectMapper()
                    val jsonObject = JSONObject(inputStreamReader.readText())
                    val userFromJson = mapper.readValue<Array<QuoteModel>>(jsonObject.getString("quotes"))

                    callback(userFromJson)

                    inputStreamReader.close()
                    inputSystem.close()
                }
                else {
                    // TODO: failed to get data
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun fetchProductsData(callback: (productInfo: Array<ProductModel>) -> Unit): Thread {
        return Thread {
            try {
                val url = URL("${_baseUrl}/products")
                val connection = url.openConnection() as HttpsURLConnection

                if (connection.responseCode == 200) {
                    val inputSystem = connection.inputStream
                    val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")

                    val mapper = jacksonObjectMapper()
                    val jsonObject = JSONObject(inputStreamReader.readText())
                    val userFromJson =
                        mapper.readValue<Array<ProductModel>>(jsonObject.getString("products"))

                    callback(userFromJson)

                    inputStreamReader.close()
                    inputSystem.close()
                }
                else {
                    // TODO: failed to get data
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}