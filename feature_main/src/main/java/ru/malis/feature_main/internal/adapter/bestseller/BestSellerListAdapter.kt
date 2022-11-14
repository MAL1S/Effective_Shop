package ru.malis.feature_main.internal.adapter.bestseller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.malis.feature_main.R
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.malis.core_domain.models.BestSeller
import ru.malis.feature_main.databinding.ItemBestSellerBinding

internal class BestSellerListAdapter(
    private val onBestSellerClickListener: OnBestSellerClickListener
): RecyclerView.Adapter<BestSellerListAdapter.BestSellerViewHolder>() {

    inner class BestSellerViewHolder(
        inflater: LayoutInflater,
        private val parent: ViewGroup,
        private val onBestSellerClickListener: OnBestSellerClickListener
    ): RecyclerView.ViewHolder(inflater.inflate(R.layout.item_best_seller, parent, false)) {

        private val binding by viewBinding(ItemBestSellerBinding::bind)

        fun bind(bestSeller: BestSeller) {
            bestSeller.pictureUrl?.let {
                uploadImage(it)
            }

            binding.apply {
                itemBestSellerTvPriceCurrent.text = bestSeller.discountPrice.toString()
                itemBestSellerTvPriceBefore.text = bestSeller.priceWithoutDiscount.toString()
                
                itemBestSellerBtnFavorite.setOnClickListener { 
                    onBestSellerClickListener.onFavoriteClicked(bestSeller)
                }
            }
            
            itemView.setOnClickListener(null)
            itemView.setOnClickListener {
                onBestSellerClickListener.onItemClicked(bestSeller)
            }
        }

        private fun uploadImage(url: String) {
            Glide.with(parent.context)
                .load(url)
                .into(binding.itemBestSellerIvProduct)
        }
    }

    var bestSellers = mutableListOf<BestSeller>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BestSellerViewHolder(inflater, parent, onBestSellerClickListener)
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        val bestSeller = bestSellers[position]
        holder.bind(bestSeller)
    }

    override fun getItemCount(): Int = bestSellers.size
}

internal interface OnBestSellerClickListener {

    fun onItemClicked(bestSeller: BestSeller)
    
    fun onFavoriteClicked(bestSeller: BestSeller)
}