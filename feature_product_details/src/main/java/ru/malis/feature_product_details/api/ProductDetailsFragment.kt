package ru.malis.feature_product_details.api

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.Lazy
import ru.malis.feature_product_details.R
import ru.malis.feature_product_details.databinding.FragmentProductDetailsBinding
import ru.malis.feature_product_details.internal.ProductDetailsComponentViewModel
import ru.malis.feature_product_details.internal.ProductDetailsViewModel
import ru.malis.feature_product_details.internal.ProductDetailsViewModelFactory
import javax.inject.Inject

class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {

    @Inject
    internal lateinit var productDetailsViewModelFactory: Lazy<ProductDetailsViewModelFactory>

    private val binding by viewBinding(FragmentProductDetailsBinding::bind)
    private val productDetailsViewModel: ProductDetailsViewModel by viewModels {
        productDetailsViewModelFactory.get()
    }
    private val componentViewModel: ProductDetailsComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        componentViewModel.productDetailsComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        initViewPagerTabs()

        binding.productDetailsBtnBack.setOnClickListener {
            productDetailsViewModel.navigateBack(this)
        }
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