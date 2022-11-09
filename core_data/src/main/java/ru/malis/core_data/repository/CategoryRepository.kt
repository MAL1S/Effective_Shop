package ru.malis.core_data.repository

import ru.malis.core_domain.models.Category

interface CategoryRepository {

    suspend fun getRepositories(): List<Category>
}