package ru.malis.effectiveshop.navigation

import ru.malis.effectiveshop.navigation.Routes.navigateToProductDetails
import ru.malis.feature_main.api.MainFragment
import ru.malis.feature_main.api.MainNavigation
import javax.inject.Inject

class MainNavigationImpl @Inject constructor(): MainNavigation {

    override fun navigateToProductDetails(fragment: MainFragment, id: Int) {
        fragment.navigateToProductDetails()
    }
}