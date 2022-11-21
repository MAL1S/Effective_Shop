package ru.malis.feature_cart.internal.di

import dagger.Component
import ru.malis.feature_cart.api.CartDeps
import ru.malis.feature_cart.api.CartFragment
import javax.inject.Scope

@Scope
annotation class CartScope

@[CartScope Component(
    dependencies = [CartDeps::class]
)]
internal interface CartComponent {

    @Component.Factory
    interface Factory {

        fun create(
            cartDeps: CartDeps
        ): CartComponent
    }

    fun inject(cartFragment: CartFragment)
}