package ru.malis.effectiveshop.navigation

import ru.malis.core_domain.models.ProductDetails
import ru.malis.effectiveshop.navigation.Routes.navigateToCart
import ru.malis.effectiveshop.navigation.Routes.navigateToProductDetails
import ru.malis.effectiveshop.navigation.Routes.popBackStack
import ru.malis.feature_main.api.MainFragment
import ru.malis.feature_main.api.MainNavigation
import ru.malis.feature_product_details.api.ProductDetailsFragment
import ru.malis.feature_product_details.api.ProductDetailsNavigation
import javax.inject.Inject

class ProductDetailsNavigationImpl @Inject constructor(): ProductDetailsNavigation {

    override fun navigateBackToMain(fragment: ProductDetailsFragment) {
        fragment.popBackStack()
    }

    override fun navigateToCart(fragment: ProductDetailsFragment) {
        fragment.navigateToCart()
    }
}