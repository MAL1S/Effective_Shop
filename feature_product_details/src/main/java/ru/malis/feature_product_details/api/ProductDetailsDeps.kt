package ru.malis.feature_product_details.api

import android.app.Application
import android.content.Context
import ru.malis.core_domain.usecase.category.GetCategoriesUseCase
import ru.malis.core_domain.usecase.product.GetProductDetailsUseCase
import ru.malis.core_domain.usecase.product.GetProductUseCase

interface ProductDetailsDeps {

    val getProductDetailsUseCase: GetProductDetailsUseCase
}

interface ProductDetailsDepsProvider {

    val productDetailsDeps: ProductDetailsDeps
}

val Context.productDetailsDepsProvider: ProductDetailsDepsProvider
    get() = when (this) {
        is ProductDetailsDepsProvider -> this
        is Application -> error("Application must implement MainDepsProvider")
        else -> applicationContext.productDetailsDepsProvider
    }