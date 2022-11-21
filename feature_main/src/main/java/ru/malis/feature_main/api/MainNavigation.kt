package ru.malis.feature_main.api

import ru.malis.core_domain.models.base.BaseProductClass

interface MainNavigation {

    fun navigateToProductDetails(fragment: MainFragment, baseProductClass: BaseProductClass)

    fun navigateToCart(fragment: MainFragment)
}