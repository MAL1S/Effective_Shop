package ru.malis.core_domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ru.malis.core_domain.models.base.BaseIdClass

@Entity
data class BestSeller(
    @PrimaryKey override val id: Int? = null,
    @SerializedName("is_favorites") val isFavorites: Boolean = false,
    val title: String? = null,
    @SerializedName("price_without_discount") val priceWithoutDiscount: Int? = null,
    @SerializedName("discount_price") val discountPrice: Int? = null,
    @SerializedName("picture") val pictureUrl: String? = null
): BaseIdClass()
