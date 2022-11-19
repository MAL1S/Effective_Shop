package ru.malis.core_domain.repository

import ru.malis.core_domain.models.Category
import ru.malis.core_domain.models.Product
import ru.malis.core_domain.models.ProductDetails

interface ProductRepository {

    suspend fun getProductByCategory(category: Category): Product

    suspend fun getProductDetails(): ProductDetails
}