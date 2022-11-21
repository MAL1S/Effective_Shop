package ru.malis.core_domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ru.malis.core_domain.models.base.BaseIdClass
import ru.malis.core_domain.models.base.BaseProductClass

@Parcelize
@Entity
data class BestSeller(
    @PrimaryKey override val id: Int = 0,
    @SerializedName("is_favorites") override val isFavorites: Boolean = false,
    override val title: String? = null,
    @SerializedName("price_without_discount") val priceWithoutDiscount: Int? = null,
    @SerializedName("discount_price") override val price: Int? = null,
    @SerializedName("picture") override val pictureUrl: String? = null
): BaseProductClass()
