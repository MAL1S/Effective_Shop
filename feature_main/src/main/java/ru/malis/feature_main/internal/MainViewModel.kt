package ru.malis.feature_main.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.malis.core_domain.models.BestSeller
import ru.malis.core_domain.models.Category
import ru.malis.core_domain.models.HotSale
import ru.malis.core_domain.models.Product
import ru.malis.core_domain.usecase.category.GetCategoriesUseCase
import ru.malis.core_domain.usecase.product.GetProductUseCase
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {

    private val _categoriesSharedFlow: MutableStateFlow<List<Category>> =
        MutableStateFlow(emptyList())
    val categoriesSharedFlow = _categoriesSharedFlow.asSharedFlow()

    private val _hotSalesSharedFlow: MutableStateFlow<List<HotSale>> =
        MutableStateFlow(emptyList())
    val hotSalesSharedFlow = _hotSalesSharedFlow.asSharedFlow()

    private val _bestSellerSharedFlow: MutableSharedFlow<List<BestSeller>> =
        MutableSharedFlow()
    val bestSellerSharedFlow = _bestSellerSharedFlow.asSharedFlow()

    fun getCategories() {
        viewModelScope.launch {
            val categories = try {
                getCategoriesUseCase.invoke()
            } catch (e: Exception) {
                null
            }

            if (categories != null) {
                _categoriesSharedFlow.emit(categories)
            }
        }
    }

    fun getProduct(category: Category) {
        viewModelScope.launch {
            val product = try {
                getProductUseCase.invoke(category)
            } catch (e: Exception) {
                null
            }

            if (product != null) {
                _hotSalesSharedFlow.emit(product.hotSale)
                _bestSellerSharedFlow.emit(product.bestSale)
            }
        }
    }
}