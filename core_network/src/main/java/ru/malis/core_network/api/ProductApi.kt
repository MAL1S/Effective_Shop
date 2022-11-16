package ru.malis.core_network.api

import retrofit2.http.GET
import ru.malis.core_domain.models.Product

interface ProductApi {

    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getProductByCategory(
        //category: String
    ): Product
}