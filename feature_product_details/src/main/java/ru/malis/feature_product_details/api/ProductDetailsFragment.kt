package ru.malis.feature_product_details.api

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import ru.malis.feature_product_details.R
import ru.malis.feature_product_details.databinding.FragmentProductDetailsBinding

class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {

    private val binding by viewBinding(FragmentProductDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        initViewPagerTabs()
    }

    private fun initViewPagerTabs() {
        TabLayoutMediator(binding.productDetailsTabLayout, binding.productDetailsViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(ru.malis.core_style.R.string.shop)
                1 -> getString(ru.malis.core_style.R.string.details)
                2 -> getString(ru.malis.core_style.R.string.features)
                else -> getString(ru.malis.core_style.R.string.shop)
            }
        }
    }
}