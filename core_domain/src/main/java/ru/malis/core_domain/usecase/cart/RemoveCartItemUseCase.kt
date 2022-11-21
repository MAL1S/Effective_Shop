package ru.malis.core_domain.usecase.cart

import ru.malis.core_domain.models.CartItem
import ru.malis.core_domain.repository.CartRepository
import javax.inject.Inject

class RemoveCartItemUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend fun invoke(id: Int) {
        return cartRepository.removeCartItemById(id)
    }
}