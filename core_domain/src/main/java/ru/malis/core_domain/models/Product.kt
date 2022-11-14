package ru.malis.core_domain.models

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("home_store") val hotSale: List<HotSale>,
    @SerializedName("best_seller") val bestSale: List<BestSeller>
)