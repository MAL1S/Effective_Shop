package ru.malis.feature_product_details.internal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.malis.feature_product_details.internal.di.DaggerProductDetailsComponent
import ru.malis.feature_product_details.api.productDetailsDepsProvider
import ru.malis.feature_product_details.internal.di.ProductDetailsComponent

internal class ProductDetailsComponentViewModel(
    application: Application
): AndroidViewModel(application) {

    val productDetailsComponent: ProductDetailsComponent by lazy {
        DaggerProductDetailsComponent.factory().create(
            productDetailsDeps = application.productDetailsDepsProvider.productDetailsDeps
        )
    }
}