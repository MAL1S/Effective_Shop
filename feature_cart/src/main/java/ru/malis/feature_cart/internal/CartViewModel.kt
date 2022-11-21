package ru.malis.feature_cart.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.malis.core_domain.models.CartItem
import ru.malis.core_domain.usecase.cart.GetCartItemsUseCase
import ru.malis.core_domain.usecase.cart.RemoveCartItemUseCase
import ru.malis.core_domain.usecase.cart.UpdateCartItemUseCase
import ru.malis.feature_cart.api.CartFragment
import ru.malis.feature_cart.api.CartNavigation
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val updateCartItemUseCase: UpdateCartItemUseCase,
    private val removeCartItemUseCase: RemoveCartItemUseCase,
    private val cartNavigation: CartNavigation
) : ViewModel() {

    private val _cartItemsSharedFlow: MutableStateFlow<List<CartItem>> =
        MutableStateFlow(emptyList())
    val cartItemsSharedFlow = _cartItemsSharedFlow.asSharedFlow()

//    fun getCategories() {
//        viewModelScope.launch {
//            val cartItems = try {
//                getCartItemsUseCase.invoke()
//            } catch (e: Exception) {
//                null
//            }
//
//            if (cartItems != null) {
//                _cartItemsSharedFlow.emit(cartItems)
//            }
//        }
//    }

    fun getCartItems(): Flow<List<CartItem>> {
        return getCartItemsUseCase.invoke()
    }

    fun removeCartItem(id: Int) {
        viewModelScope.launch {
            removeCartItemUseCase.invoke(id)
        }
    }

    fun updateCartItem(cartItem: CartItem) {
        viewModelScope.launch {
            updateCartItemUseCase.invoke(cartItem)
        }
    }

    fun navigateToMain(fragment: CartFragment) {
        cartNavigation.navigateBackToMain(fragment)
    }
}