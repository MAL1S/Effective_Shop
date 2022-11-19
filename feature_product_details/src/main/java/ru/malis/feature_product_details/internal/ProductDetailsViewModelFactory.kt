package ru.malis.feature_product_details.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.malis.core_domain.usecase.product.GetProductDetailsUseCase
import javax.inject.Inject
import javax.inject.Provider

class ProductDetailsViewModelFactory @Inject constructor(
    private val getProductDetailsUseCase: Provider<GetProductDetailsUseCase>,
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductDetailsViewModel(
            getProductDetailsUseCase = getProductDetailsUseCase.get()
        ) as T
    }
}