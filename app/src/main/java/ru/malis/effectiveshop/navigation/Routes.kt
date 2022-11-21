package ru.malis.effectiveshop.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import ru.malis.core_domain.models.base.BaseProductClass
import ru.malis.effectiveshop.R
import ru.malis.feature_cart.api.CartFragment
import ru.malis.feature_product_details.api.ProductDetailsFragment

object Routes {

    fun Fragment.navigateToProductDetails(baseProductClass: BaseProductClass) {
        val fragment = ProductDetailsFragment.newInstance(baseProductClass)
        parentFragmentManager.commit {
            setupTransaction(fragment)
            addToBackStack(null)
        }
    }

    fun Fragment.navigateToCart() {
        val fragment = CartFragment()
        parentFragmentManager.commit {
            setupTransaction(fragment)
            addToBackStack(null)
        }
    }

    fun Fragment.popBackStack() {
        parentFragmentManager.popBackStack()
    }

    private fun FragmentTransaction.setupTransaction(fragment: Fragment): FragmentTransaction {
        this.setCustomAnimations(
            ru.malis.core_style.R.anim.enter_right_to_left, //enter
            ru.malis.core_style.R.anim.exit_right_to_left, //exit
            ru.malis.core_style.R.anim.enter_left_to_right, //popEnter
            ru.malis.core_style.R.anim.exit_left_to_right //popExit
        )
        this.setReorderingAllowed(true)
        this.replace(R.id.fragment_container_view, fragment)

        return this
    }
}