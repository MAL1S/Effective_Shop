package ru.malis.core_domain.models

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("home_store") val hotSale: HotSale,
    @SerializedName("best_seller") val bestSale: BestSeller
)