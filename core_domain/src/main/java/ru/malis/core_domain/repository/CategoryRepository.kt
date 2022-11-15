package ru.malis.core_domain.repository

import ru.malis.core_domain.models.Category

interface CategoryRepository {

    suspend fun getCategories(): List<Category>
}