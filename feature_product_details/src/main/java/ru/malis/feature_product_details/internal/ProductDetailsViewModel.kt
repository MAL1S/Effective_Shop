package ru.malis.feature_product_details.internal

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.malis.core_domain.models.ProductDetails
import ru.malis.core_domain.usecase.product.GetProductDetailsUseCase
import java.lang.Exception
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.malis.feature_product_details.api.ProductDetailsFragment
import ru.malis.feature_product_details.api.ProductDetailsNavigation

internal class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val productDetailsNavigation: ProductDetailsNavigation
): ViewModel() {

    private val _productDetailsSharedFlow = MutableSharedFlow<ProductDetails?>()
    val productDetailsSharedFlow = _productDetailsSharedFlow.asSharedFlow()

    fun getProductDetails() {
        viewModelScope.launch {
            val productDetails = try {
                getProductDetailsUseCase.invoke()
            } catch (e: Exception) {
                null
            }

            _productDetailsSharedFlow.emit(productDetails)
        }
    }

    fun navigateBack(fragment: ProductDetailsFragment) {
        productDetailsNavigation.navigateBackToMain(fragment)
    }
}