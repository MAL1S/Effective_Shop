package ru.malis.feature_product_details.api

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.Lazy
import ru.malis.core_domain.models.CartItem
import ru.malis.feature_product_details.R
import ru.malis.feature_product_details.databinding.FragmentShopBinding
import ru.malis.feature_product_details.internal.ProductDetailsComponentViewModel
import ru.malis.feature_product_details.internal.ProductDetailsViewModel
import ru.malis.feature_product_details.internal.ProductDetailsViewModelFactory
import javax.inject.Inject

class ShopFragment : Fragment(R.layout.fragment_shop) {

    @Inject
    internal lateinit var productDetailsViewModelFactory: Lazy<ProductDetailsViewModelFactory>

    private val binding by viewBinding(FragmentShopBinding::bind)
    private val productDetailsViewModel: ProductDetailsViewModel by activityViewModels {
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
        initInfo()
    }

    private fun initInfo() {
        lifecycleScope.launchWhenStarted {
            productDetailsViewModel.productDetailsSharedFlow.collect { product ->
                if (product == null) return@collect
                binding.shopTvProcessor.text = product.cpu
                binding.shopTvCamera.text = product.camera
                binding.shopTvRam.text = product.ssd
                binding.shopTvMemoryCard.text = product.sd
                if (product.price != null) {
                    binding.shopTvAddToCartPrice.text = resources.getString(
                        ru.malis.core_style.R.string.cart_price,
                        product.price!!
                    )
                }
                if (product.color != null) {
                    binding.shopCheckableColorView.setColors(product.color!!)
                }
                if (product.capacity != null) {
                    binding.shopCheckableTextView.setLabels(product.capacity!!.map { "$it GB" })
                }

                if (productDetailsViewModel.baseProduct != null) {
                    binding.shopBtnAddToCart.setOnClickListener {
                        productDetailsViewModel.insertCartItem(
                            CartItem(
                                id = productDetailsViewModel.baseProduct!!.id,
                                name = productDetailsViewModel.baseProduct!!.title!!,
                                price = productDetailsViewModel.baseProduct!!.price!!,
                                amount = 1,
                                imageUrl = productDetailsViewModel.baseProduct!!.pictureUrl!!
                            )
                        )
                        Snackbar.make(binding.root, getString(ru.malis.core_style.R.string.cart_product_bought), Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}