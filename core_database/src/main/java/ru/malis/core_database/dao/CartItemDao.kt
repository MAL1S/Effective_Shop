package ru.malis.core_database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.malis.core_domain.models.CartItem

@Dao
interface CartItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItem)

    @Query("DELETE FROM CartItem WHERE id=:id")
    suspend fun deleteCartItemById(id: Int)

    @Query("SELECT * FROM CartItem")
    fun getAllCartItems(): Flow<List<CartItem>>

    @Update
    suspend fun updateCartItem(cartItem: CartItem)
}