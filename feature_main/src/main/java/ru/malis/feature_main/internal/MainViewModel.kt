package ru.malis.feature_main.internal

import androidx.lifecycle.ViewModel
import ru.malis.core_domain.usecase.category.GetCategoriesUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
): ViewModel() {
}