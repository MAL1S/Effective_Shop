package ru.malis.effectiveshop.navigation

import ru.malis.effectiveshop.navigation.Routes.popBackStack
import ru.malis.feature_cart.api.CartFragment
import ru.malis.feature_cart.api.CartNavigation
import javax.inject.Inject

class CartNavigationImpl @Inject constructor(): CartNavigation {

    override fun navigateBackToMain(fragment: CartFragment) {
        fragment.popBackStack()
    }
}