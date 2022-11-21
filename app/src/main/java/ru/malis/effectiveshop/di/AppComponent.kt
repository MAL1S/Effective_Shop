package ru.malis.effectiveshop.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.malis.effectiveshop.MainActivity
import ru.malis.feature_cart.api.CartDeps
import ru.malis.feature_main.api.MainDeps
import ru.malis.feature_product_details.api.ProductDetailsDeps
import javax.inject.Qualifier
import javax.inject.Scope

@Scope
annotation class AppScope

@[AppScope Component(
    modules = [
        DataModule::class,
        DatabaseModule::class,
        CoroutineDispatcherModule::class,
        NetworkModule::class,
        NavigationModule::class
    ]
)]
interface AppComponent : MainDeps, ProductDetailsDeps, CartDeps {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance @ApplicationContext context: Context
        ): AppComponent
    }

    fun inject(mainActivity: MainActivity)
}

@Qualifier
annotation class ApplicationContext