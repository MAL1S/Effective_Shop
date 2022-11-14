package ru.malis.core_domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ru.malis.core_domain.models.base.BaseIdClass

@Entity
data class HotSale(
    @PrimaryKey override val id: Int? = null,
    @SerializedName("is_new") val isNew: Boolean = false,
    val title: String? = null,
    val subtitle: String? = null,
    @SerializedName("picture") val pictureUrl: String? = null,
    @SerializedName("is_buy") val isBuy: Boolean = false
): BaseIdClass()
