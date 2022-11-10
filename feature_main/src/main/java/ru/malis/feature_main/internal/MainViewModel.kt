package ru.malis.feature_main.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.malis.core_domain.models.Category
import ru.malis.core_domain.usecase.category.GetCategoriesUseCase
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _categoriesStateFlow: MutableStateFlow<List<Category>> =
        MutableStateFlow(emptyList())
    val categoriesStateFlow = _categoriesStateFlow.asStateFlow()

    fun getCategories() {
        viewModelScope.launch {
            val categories = try {
                getCategoriesUseCase.invoke()
            } catch (e: Exception) {
                null
            }

            if (categories != null) {
                _categoriesStateFlow.emit(categories)
            }
        }
    }
}