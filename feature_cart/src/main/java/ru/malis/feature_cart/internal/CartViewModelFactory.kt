package ru.malis.feature_cart.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.malis.core_domain.usecase.cart.GetCartItemsUseCase
import ru.malis.core_domain.usecase.cart.RemoveCartItemUseCase
import ru.malis.core_domain.usecase.cart.UpdateCartItemUseCase
import ru.malis.feature_cart.api.CartNavigation
import javax.inject.Inject
import javax.inject.Provider

class CartViewModelFactory @Inject constructor(
    private val getCartItemsUseCase: Provider<GetCartItemsUseCase>,
    private val updateCartItemUseCase: Provider<UpdateCartItemUseCase>,
    private val removeCartItemUseCase: Provider<RemoveCartItemUseCase>,
    private val cartNavigation: Provider<CartNavigation>
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(
            getCartItemsUseCase = getCartItemsUseCase.get(),
            updateCartItemUseCase = updateCartItemUseCase.get(),
            removeCartItemUseCase = removeCartItemUseCase.get(),
            cartNavigation = cartNavigation.get()
        ) as T
    }
}