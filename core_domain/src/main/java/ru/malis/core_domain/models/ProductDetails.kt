package ru.malis.core_domain.models

import com.google.gson.annotations.SerializedName
import ru.malis.core_domain.models.base.BaseIdClass

data class ProductDetails(
    override val id: Int? = null,
    @SerializedName("CPU") val cpu: String? = null,
    val camera: String? = null,
    val capacity: List<String>? = null,
    val color: List<String>? = null,
    val images: List<String>? = null,
    val isFavorites: Boolean = false,
    val price: Int? = null,
    val rating: Float? = null,
    val sd: String? = null,
    val ssd: String? = null,
    val title: String? = null
) : BaseIdClass()
