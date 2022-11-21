package ru.malis.core_data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.malis.core_database.dao.CartItemDao
import ru.malis.core_domain.models.CartItem
import ru.malis.core_domain.repository.CartRepository
import ru.malis.core_util.coroutinedispatchers.IoDispatcher
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartItemDao: CartItemDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CartRepository {

    override suspend fun insertCartItem(cartItem: CartItem) {
        withContext(ioDispatcher) {
            cartItemDao.insertCartItem(cartItem)
        }
    }

    override fun getAllCartItems(): Flow<List<CartItem>> {
        return cartItemDao.getAllCartItems()
    }

    override suspend fun updateCartItem(cartItem: CartItem) {
        withContext(ioDispatcher) {
            cartItemDao.updateCartItem(cartItem)
        }
    }

    override suspend fun removeCartItemById(id: Int) {
        withContext(ioDispatcher) {
            cartItemDao.deleteCartItemById(id)
        }
    }
}