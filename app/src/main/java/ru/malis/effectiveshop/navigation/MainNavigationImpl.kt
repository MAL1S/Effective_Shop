package ru.malis.effectiveshop.navigation

import ru.malis.core_domain.models.base.BaseProductClass
import ru.malis.effectiveshop.navigation.Routes.navigateToCart
import ru.malis.effectiveshop.navigation.Routes.navigateToProductDetails
import ru.malis.feature_main.api.MainFragment
import ru.malis.feature_main.api.MainNavigation
import javax.inject.Inject

class MainNavigationImpl @Inject constructor(): MainNavigation {

    override fun navigateToProductDetails(fragment: MainFragment, baseProductClass: BaseProductClass) {
        fragment.navigateToProductDetails(baseProductClass)
    }

    override fun navigateToCart(fragment: MainFragment) {
        fragment.navigateToCart()
    }
}