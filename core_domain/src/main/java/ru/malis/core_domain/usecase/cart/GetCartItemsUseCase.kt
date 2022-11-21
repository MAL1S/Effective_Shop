package ru.malis.core_domain.usecase.cart

import kotlinx.coroutines.flow.Flow
import ru.malis.core_domain.models.CartItem
import ru.malis.core_domain.models.ProductDetails
import ru.malis.core_domain.repository.CartRepository
import ru.malis.core_domain.repository.ProductRepository
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    fun invoke(): Flow<List<CartItem>> {
        return cartRepository.getAllCartItems()
    }
}