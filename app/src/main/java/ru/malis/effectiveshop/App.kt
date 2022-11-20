package ru.malis.effectiveshop

import android.app.Application
import ru.malis.effectiveshop.di.AppComponent
import ru.malis.effectiveshop.di.DaggerAppComponent
import ru.malis.feature_main.api.MainDeps
import ru.malis.feature_main.api.MainDepsProvider
import ru.malis.feature_product_details.api.ProductDetailsDeps
import ru.malis.feature_product_details.api.ProductDetailsDepsProvider

class App: Application(), MainDepsProvider, ProductDetailsDepsProvider {

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(
                context = this
            )
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    override val mainDeps: MainDeps = appComponent
    override val productDetailsDeps: ProductDetailsDeps = appComponent
}