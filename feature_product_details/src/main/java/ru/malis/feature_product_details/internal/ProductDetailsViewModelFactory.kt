package ru.malis.feature_product_details.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.malis.core_domain.usecase.cart.InsertCartItemUseCase
import ru.malis.core_domain.usecase.product.GetProductDetailsUseCase
import ru.malis.feature_product_details.api.ProductDetailsNavigation
import javax.inject.Inject
import javax.inject.Provider

class ProductDetailsViewModelFactory @Inject constructor(
    private val getProductDetailsUseCase: Provider<GetProductDetailsUseCase>,
    private val productDetailsNavigation: Provider<ProductDetailsNavigation>,
    private val insertCartItemUseCase: Provider<InsertCartItemUseCase>
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductDetailsViewModel(
            getProductDetailsUseCase = getProductDetailsUseCase.get(),
            productDetailsNavigation = productDetailsNavigation.get(),
            insertCartItemUseCase = insertCartItemUseCase.get()
        ) as T
    }
}