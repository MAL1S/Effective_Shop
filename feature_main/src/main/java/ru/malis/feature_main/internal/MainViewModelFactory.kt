package ru.malis.feature_main.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.malis.core_domain.usecase.category.GetCategoriesUseCase
import ru.malis.core_domain.usecase.product.GetProductUseCase
import javax.inject.Inject
import javax.inject.Provider

class MainViewModelFactory @Inject constructor(
    private val getCategoriesUseCase: Provider<GetCategoriesUseCase>,
    private val getProductUseCase: Provider<GetProductUseCase>
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getCategoriesUseCase = getCategoriesUseCase.get(),
            getProductUseCase = getProductUseCase.get()
        ) as T
    }
}