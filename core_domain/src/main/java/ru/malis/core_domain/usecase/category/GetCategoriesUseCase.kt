package ru.malis.core_domain.usecase.category

import ru.malis.core_domain.repository.CategoryRepository
import ru.malis.core_domain.models.Category
import ru.malis.core_domain.usecase.BaseUseCase
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend fun invoke(selectedId: Int? = null): List<Category> {
        var categories = categoryRepository.getCategories()

        if (selectedId == null) {
            categories[0].isSelected = true
        } else {
            categories = categories.map {
                if (it.id == selectedId) it.copy(isSelected = true)
                else it
            }
        }

        return categories
    }
}