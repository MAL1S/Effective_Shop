package ru.malis.feature_product_details.internal.di

import dagger.Component
import ru.malis.feature_product_details.api.ProductDetailsDeps
import ru.malis.feature_product_details.api.ProductDetailsFragment
import ru.malis.feature_product_details.api.ShopFragment
import javax.inject.Scope

@Scope
annotation class ProductDetailsScope

@[ProductDetailsScope Component(
    dependencies = [ProductDetailsDeps::class]
)]
internal interface ProductDetailsComponent {

    @Component.Factory
    interface Factory {

        fun create(
            productDetailsDeps: ProductDetailsDeps
        ): ProductDetailsComponent
    }

    fun inject(productDetailsFragment: ProductDetailsFragment)
    fun inject(shopFragment: ShopFragment)
}