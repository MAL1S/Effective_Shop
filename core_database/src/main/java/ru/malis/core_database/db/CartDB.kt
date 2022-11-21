package ru.malis.core_database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.malis.core_database.dao.CartItemDao
import ru.malis.core_domain.models.CartItem

@Database(
    entities = [
        CartItem::class
    ], version = 1
)
abstract class CartDB : RoomDatabase() {

    abstract fun getCartItemDao(): CartItemDao
}