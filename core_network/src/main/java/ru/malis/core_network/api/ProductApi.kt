package ru.malis.core_network.api

import retrofit2.http.GET
import ru.malis.core_domain.models.Product
import ru.malis.core_domain.models.ProductDetails

interface ProductApi {

    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getProductByCategory(
        //category: String
    ): Product

    @GET("6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getProductDetails(): ProductDetails
}