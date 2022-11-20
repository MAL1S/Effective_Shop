package ru.malis.feature_product_details.internal

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.malis.feature_product_details.api.ShopFragment

class ProductDetailsPagerAdapter(
    activity: FragmentManager,
    lifecycle: Lifecycle,
): FragmentStateAdapter(activity, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> ShopFragment()
            else -> ShopFragment()
        }
    }
}