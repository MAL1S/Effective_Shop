package ru.malis.feature_cart.internal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.malis.feature_cart.api.cartDepsProvider
import ru.malis.feature_cart.internal.di.CartComponent
import ru.malis.feature_cart.internal.di.DaggerCartComponent

internal class CartComponentViewModel(
    application: Application
): AndroidViewModel(application) {

    val mainComponent: CartComponent by lazy {
        DaggerCartComponent.factory().create(
            cartDeps = application.cartDepsProvider.cartDeps
        )
    }
}