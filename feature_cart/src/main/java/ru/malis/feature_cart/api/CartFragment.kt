package ru.malis.feature_cart.api

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import ru.malis.core_base.BaseDiffUtilCallback
import ru.malis.core_domain.models.CartItem
import ru.malis.feature_cart.R
import ru.malis.feature_cart.databinding.FragmentCartBinding
import ru.malis.feature_cart.internal.CartComponentViewModel
import ru.malis.feature_cart.internal.CartViewModel
import ru.malis.feature_cart.internal.CartViewModelFactory
import ru.malis.feature_cart.internal.adapter.CartItemListAdapter
import ru.malis.feature_cart.internal.adapter.OnCartItemClickedListener
import javax.inject.Inject

class CartFragment : Fragment(R.layout.fragment_cart) {

    @Inject
    internal lateinit var cartViewModelFactory: Lazy<CartViewModelFactory>

    private val binding by viewBinding(FragmentCartBinding::bind)
    private val cartViewModel: CartViewModel by viewModels {
        cartViewModelFactory.get()
    }
    private val componentViewModel: CartComponentViewModel by viewModels()

    private var recyclerViewState: Parcelable? = null

    private var resultPrice: Int = 0

    private val cartItemListAdapter = CartItemListAdapter(object : OnCartItemClickedListener {

        override fun onAddClicked(cartItem: CartItem) {
            cartViewModel.updateCartItem(cartItem)
        }

        override fun onRemoveClicked(cartItem: CartItem) {
            cartViewModel.updateCartItem(cartItem)
        }

        override fun onDeleteClicked(cartItem: CartItem) {
            cartViewModel.removeCartItem(cartItem.id)
        }
    })

    override fun onAttach(context: Context) {
        super.onAttach(context)

        componentViewModel.mainComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        initCartItems()

        binding.productDetailsBtnBack.setOnClickListener {
            cartViewModel.navigateToMain(this)
        }

        binding.cartTvDeliveryResult.text = "Free"
    }

    private fun initCartItems() {
        lifecycleScope.launchWhenStarted {
            cartViewModel.getCartItems().collect {
                recyclerViewState = binding.cartRcvProducts.layoutManager?.onSaveInstanceState();
                cartItemListAdapter.cartItems = it.toMutableList()
                binding.cartRcvProducts.adapter = cartItemListAdapter

                triggerCartItemDiffUtil(it)

                binding.cartTvEmpty.visibility = if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
                binding.cartRcvProducts.visibility = if (it.isNotEmpty()) View.VISIBLE else View.INVISIBLE
                binding.cartRcvProducts.layoutManager?.onRestoreInstanceState(recyclerViewState)

                resultPrice = 0

                for (item in it) {
                    resultPrice += item.price * item.amount
                }

                binding.cartTvTotalResult.text = resources.getString(
                    ru.malis.core_style.R.string.cart_price,
                    resultPrice
                )
            }
        }
    }

    private fun triggerCartItemDiffUtil(newCartItems: List<CartItem>) {
        val diffUtilCallback =
            BaseDiffUtilCallback(cartItemListAdapter.cartItems, newCartItems)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        cartItemListAdapter.cartItems = newCartItems.toMutableList()
        diffResult.dispatchUpdatesTo(cartItemListAdapter)
    }
}