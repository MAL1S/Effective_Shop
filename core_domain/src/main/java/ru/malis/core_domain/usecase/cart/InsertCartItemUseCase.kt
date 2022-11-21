package ru.malis.core_domain.usecase.cart

import ru.malis.core_domain.models.CartItem
import ru.malis.core_domain.models.ProductDetails
import ru.malis.core_domain.repository.CartRepository
import ru.malis.core_domain.repository.ProductRepository
import javax.inject.Inject

class InsertCartItemUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend fun invoke(cartItem: CartItem) {
        return cartRepository.insertCartItem(cartItem)
    }
}