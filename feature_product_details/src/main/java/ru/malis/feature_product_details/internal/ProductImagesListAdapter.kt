package ru.malis.feature_product_details.internal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.malis.core_domain.models.HotSale
import ru.malis.feature_product_details.R
import ru.malis.feature_product_details.databinding.ItemProductImageBinding

internal class ProductImagesListAdapter(
    var deviceWidth: Int
): RecyclerView.Adapter<ProductImagesListAdapter.ProductImagesListViewHolder>() {

    inner class ProductImagesListViewHolder(
        inflater: LayoutInflater,
        private val parent: ViewGroup,
    ): RecyclerView.ViewHolder(inflater.inflate(R.layout.item_product_image, parent, false)) {

        private val binding by viewBinding(ItemProductImageBinding::bind)

        fun bind(imageUrl: String) {
            uploadImage(imageUrl)
        }

        private fun uploadImage(url: String) {
            Glide.with(parent.context)
                .load(url)
                .placeholder(ru.malis.core_style.R.drawable.ic_image_placeholder)
                .into(binding.itemHotSaleBackground)
        }
    }

    var images = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImagesListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductImagesListViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProductImagesListViewHolder, position: Int) {
        if (images.size != 0) {
            val image = images[position % images.size]
            holder.bind(image)

            holder.itemView.layoutParams.width = (deviceWidth * 0.65).toInt()
        }
    }

    override fun getItemCount(): Int = Int.MAX_VALUE
}