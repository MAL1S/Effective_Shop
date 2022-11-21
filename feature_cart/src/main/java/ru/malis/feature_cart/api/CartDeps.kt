package ru.malis.feature_cart.api

import android.app.Application
import android.content.Context
import ru.malis.core_domain.usecase.cart.GetCartItemsUseCase
import ru.malis.core_domain.usecase.cart.RemoveCartItemUseCase
import ru.malis.core_domain.usecase.cart.UpdateCartItemUseCase

interface CartDeps {

    val getCartItemsUseCase: GetCartItemsUseCase
    val updateCartItemUseCase: UpdateCartItemUseCase
    val removeCartItemUseCase: RemoveCartItemUseCase
    val cartNavigation: CartNavigation
}

interface CartDepsProvider {

    val cartDeps: CartDeps
}

val Context.cartDepsProvider: CartDepsProvider
    get() = when (this) {
        is CartDepsProvider -> this
        is Application -> error("Application must implement CartDepsProvider")
        else -> applicationContext.cartDepsProvider
    }