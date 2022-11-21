package ru.malis.feature_cart.internal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.malis.core_domain.models.CartItem
import ru.malis.core_domain.models.HotSale
import ru.malis.feature_cart.R
import ru.malis.feature_cart.databinding.ItemCartProductBinding


internal class CartItemListAdapter(
    private val onCartItemClickedListener: OnCartItemClickedListener
) : RecyclerView.Adapter<CartItemListAdapter.CartItemViewHolder>() {

    inner class CartItemViewHolder(
        inflater: LayoutInflater,
        private val parent: ViewGroup,
        private val onCartItemClickedListener: OnCartItemClickedListener
    ) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_cart_product, parent, false)) {

        private val binding by viewBinding(ItemCartProductBinding::bind)

        fun bind(cartItem: CartItem) {
            uploadImage(cartItem.imageUrl)

            binding.apply {
                itemCartProductTvName.text = cartItem.name
                itemCartProductTvAmount.text = cartItem.amount.toString()
                itemCartProductTvPrice.text = parent.context.resources.getString(
                    ru.malis.core_style.R.string.cart_price,
                    cartItem.price * cartItem.amount
                )
            }

            binding.itemCartProductBtnAddItem.setOnClickListener(null)
            binding.itemCartProductBtnAddItem.setOnClickListener {
                onCartItemClickedListener.onAddClicked(cartItem.copy(amount = cartItem.amount + 1))
            }

            binding.itemCartProductBtnRemoveItem.setOnClickListener(null)
            binding.itemCartProductBtnRemoveItem.setOnClickListener {
                if (cartItem.amount == 1) {
                    onCartItemClickedListener.onDeleteClicked(cartItem)
                    return@setOnClickListener
                }
                onCartItemClickedListener.onRemoveClicked(cartItem.copy(amount = cartItem.amount - 1))
            }

            binding.itemCartProductBtnDelete.setOnClickListener(null)
            binding.itemCartProductBtnDelete.setOnClickListener {
                onCartItemClickedListener.onDeleteClicked(cartItem)
            }
        }

        private fun uploadImage(url: String) {
            Glide.with(parent.context)
                .load(url)
                .placeholder(ru.malis.core_style.R.drawable.ic_image_placeholder)
                .into(binding.itemCartProductIvImage)
        }
    }

    var cartItems = mutableListOf<CartItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CartItemViewHolder(inflater, parent, onCartItemClickedListener)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.bind(cartItem)
    }

    override fun getItemCount(): Int = cartItems.size
}

internal interface OnCartItemClickedListener {

    fun onAddClicked(cartItem: CartItem)
    fun onRemoveClicked(cartItem: CartItem)
    fun onDeleteClicked(cartItem: CartItem)
}