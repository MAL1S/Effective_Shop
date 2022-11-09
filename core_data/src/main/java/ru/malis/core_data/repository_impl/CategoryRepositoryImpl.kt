package ru.malis.core_data.repository_impl

import ru.malis.core_data.repository.CategoryRepository
import ru.malis.core_domain.models.Category

class CategoryRepositoryImpl(

): CategoryRepository {

    override suspend fun getRepositories(): List<Category> {
        TODO("Not yet implemented")
    }
}