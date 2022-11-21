package ru.malis.core_domain.repository

import ru.malis.core_domain.models.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    suspend fun insertCartItem(cartItem: CartItem)

    fun getAllCartItems(): Flow<List<CartItem>>

    suspend fun updateCartItem(cartItem: CartItem)

    suspend fun removeCartItemById(id: Int)
}