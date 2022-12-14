package ru.malis.feature_main.api

import android.app.Application
import android.content.Context
import ru.malis.core_domain.usecase.cart.GetCartItemsUseCase
import ru.malis.core_domain.usecase.category.GetCategoriesUseCase
import ru.malis.core_domain.usecase.product.GetProductUseCase

interface MainDeps {

    val getCategoriesUseCase: GetCategoriesUseCase
    val getProductUseCase: GetProductUseCase
    val mainNavigation: MainNavigation
    val getCartItemUseCase: GetCartItemsUseCase
}

interface MainDepsProvider {

    val mainDeps: MainDeps
}

val Context.mainDepsProvider: MainDepsProvider
    get() = when (this) {
        is MainDepsProvider -> this
        is Application -> error("Application must implement MainDepsProvider")
        else -> applicationContext.mainDepsProvider
    }