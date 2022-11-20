package ru.malis.core_domain.usecase.product

import ru.malis.core_domain.models.ProductDetails
import ru.malis.core_domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    suspend fun invoke(): ProductDetails {
        return productRepository.getProductDetails()
    }
}