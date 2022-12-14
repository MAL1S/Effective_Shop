package ru.malis.core_domain.usecase.product

import ru.malis.core_domain.models.Category
import ru.malis.core_domain.models.Product
import ru.malis.core_domain.repository.ProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    suspend fun invoke(category: Category): Product {
        return productRepository.getProductByCategory(category)
    }
}