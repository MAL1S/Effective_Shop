package ru.malis.core_domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.malis.core_domain.models.base.BaseIdClass

@Entity
data class CartItem(
    @PrimaryKey override val id: Int = 0,
    val name: String,
    val price: Int,
    val amount: Int,
    val imageUrl: String
): BaseIdClass()
