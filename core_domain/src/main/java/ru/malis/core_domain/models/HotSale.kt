package ru.malis.core_domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ru.malis.core_domain.models.base.BaseIdClass
import ru.malis.core_domain.models.base.BaseProductClass

@Parcelize
@Entity
data class HotSale(
    @PrimaryKey override val id: Int = 0,
    @SerializedName("is_new") val isNew: Boolean = false,
    override val title: String? = null,
    val subtitle: String? = null,
    @SerializedName("picture") override val pictureUrl: String? = null,
    @SerializedName("is_buy") val isBuy: Boolean = false
): BaseProductClass()
