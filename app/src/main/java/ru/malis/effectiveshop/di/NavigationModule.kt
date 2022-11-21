package ru.malis.effectiveshop.di

import dagger.Binds
import dagger.Module
import ru.malis.effectiveshop.navigation.CartNavigationImpl
import ru.malis.effectiveshop.navigation.MainNavigationImpl
import ru.malis.effectiveshop.navigation.ProductDetailsNavigationImpl
import ru.malis.feature_cart.api.CartNavigation
import ru.malis.feature_main.api.MainNavigation
import ru.malis.feature_product_details.api.ProductDetailsNavigation

@Module
interface NavigationModule {

    @AppScope
    @Binds
    fun bindMainNavigation(mainNavigationImpl: MainNavigationImpl): MainNavigation

    @AppScope
    @Binds
    fun bindProductDetailsNavigation(productDetailsNavigationImpl: ProductDetailsNavigationImpl): ProductDetailsNavigation

    @AppScope
    @Binds
    fun bindCartNavigation(cartNavigationImpl: CartNavigationImpl): CartNavigation
}