package ru.malis.feature_product_details.api

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.Lazy
import ru.malis.core_base.CenterSmoothScroller
import ru.malis.core_base.HorizontalSpaceItemDecorator
import ru.malis.feature_product_details.R
import ru.malis.feature_product_details.databinding.FragmentProductDetailsBinding
import ru.malis.feature_product_details.internal.*
import ru.malis.feature_product_details.internal.ProductDetailsComponentViewModel
import ru.malis.feature_product_details.internal.ProductDetailsViewModel
import ru.malis.feature_product_details.internal.ProductImagesListAdapter
import javax.inject.Inject

class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {

    @Inject
    internal lateinit var productDetailsViewModelFactory: Lazy<ProductDetailsViewModelFactory>

    private val binding by viewBinding(FragmentProductDetailsBinding::bind)
    private val productDetailsViewModel: ProductDetailsViewModel by activityViewModels {
        productDetailsViewModelFactory.get()
    }
    private val componentViewModel: ProductDetailsComponentViewModel by viewModels()

    private val productImagesListAdapter = ProductImagesListAdapter(0)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        componentViewModel.productDetailsComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        initViewPager()
        initProductImages()

        binding.productDetailsBtnBack.setOnClickListener {
            productDetailsViewModel.navigateBack(this)
        }
    }

    private fun initProductImages() {
        binding.productDetailsRcvCarouselImages.apply {
            binding.productDetailsRcvCarouselImages.initialize(productImagesListAdapter)
            addItemDecoration(HorizontalSpaceItemDecorator())
        }

        lifecycleScope.launchWhenStarted {
            productDetailsViewModel.productDetailsSharedFlow.collect { product ->
                val displayMetrics = DisplayMetrics()
                (requireContext() as Activity).windowManager.defaultDisplay.getMetrics(
                    displayMetrics
                )
                productImagesListAdapter.deviceWidth = displayMetrics.widthPixels

                if (product?.images != null) {
                    productImagesListAdapter.images = product.images!!.toMutableList()
                }
                binding.productDetailsRcvCarouselImages.initialize(productImagesListAdapter)
                binding.productDetailsRcvCarouselImages.adapter?.notifyDataSetChanged()
                binding.productDetailsRcvCarouselImages.layoutManager?.scrollToPosition(Int.MAX_VALUE / 2)

                val smoothScroller = CenterSmoothScroller(requireContext())
                smoothScroller.targetPosition = Int.MAX_VALUE / 2
                binding.productDetailsRcvCarouselImages.layoutManager?.startSmoothScroll(
                    smoothScroller
                )

                binding.productDetailsName.text = product?.title
                if (product?.rating != null) {
                    binding.productDetailsRatingBar.rating = product.rating!!
                }
            }
        }

        productDetailsViewModel.getProductDetails()
    }

    private fun initViewPager() {
        binding.productDetailsViewPager.adapter =
            ProductDetailsPagerAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(
            binding.productDetailsTabLayout,
            binding.productDetailsViewPager
        ) { tab, position ->
            tab.text = when (position) {
                0 -> getString(ru.malis.core_style.R.string.shop)
                1 -> getString(ru.malis.core_style.R.string.details)
                2 -> getString(ru.malis.core_style.R.string.features)
                else -> getString(ru.malis.core_style.R.string.shop)
            }
        }.attach()
    }
}