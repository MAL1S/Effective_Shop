package ru.malis.core_domain.usecase.category

import ru.malis.core_domain.repository.CategoryRepository
import ru.malis.core_domain.models.Category
import ru.malis.core_domain.usecase.BaseUseCase
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend fun invoke(): List<Category> {
        return categoryRepository.getCategories()
    }
}