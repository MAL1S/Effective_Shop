package ru.malis.core_domain.repository

import ru.malis.core_domain.models.Category
import ru.malis.core_domain.models.Product

interface ProductRepository {

    suspend fun getProductByCategory(category: Category): Product
}