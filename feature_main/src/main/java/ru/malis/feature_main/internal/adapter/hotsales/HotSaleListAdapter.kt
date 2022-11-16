package ru.malis.feature_main.internal.adapter.hotsales

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.malis.feature_main.R
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.malis.core_domain.models.HotSale
import ru.malis.feature_main.databinding.ItemHotSaleBinding

internal class HotSaleListAdapter(
    private val onHotSalesClickedListener: OnHotSaleClickedListener,
    val itemClick: (position:Int,item: HotSale) -> Unit
): RecyclerView.Adapter<HotSaleListAdapter.HotSaleViewHolder>() {

    inner class HotSaleViewHolder(
        inflater: LayoutInflater,
        private val parent: ViewGroup,
        private val onHotSaleClickedListener: OnHotSaleClickedListener
    ): RecyclerView.ViewHolder(inflater.inflate(R.layout.item_hot_sale, parent, false)) {

        private val binding by viewBinding(ItemHotSaleBinding::bind)

        fun bind(hotSale: HotSale) {
            hotSale.pictureUrl?.let {
                uploadImage(it)
            }

            binding.apply {
                itemHotSaleTvNew.visibility = if (hotSale.isNew) View.VISIBLE else View.INVISIBLE
                itemHotSaleTvTitle.text = hotSale.title
                itemHotSaleTvSubtitle.text = hotSale.subtitle
            }

            itemView.setOnClickListener(null)
            itemView.setOnClickListener {
                onHotSaleClickedListener.onClicked(hotSale)
            }
        }

        private fun uploadImage(url: String) {
            Glide.with(parent.context)
                .load(url)
                .placeholder(ru.malis.core_style.R.drawable.ic_image_placeholder)
                .into(binding.itemHotSaleBackground)
        }
    }

    var hotSales = mutableListOf<HotSale>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotSaleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HotSaleViewHolder(inflater, parent, onHotSalesClickedListener)
    }

    override fun onBindViewHolder(holder: HotSaleViewHolder, position: Int) {
        val hotSale = hotSales[position]
        holder.bind(hotSale)
        holder.itemView.setOnClickListener {
            itemClick(position, hotSales[position])
        }
    }

    override fun getItemCount(): Int = hotSales.size
}

internal fun interface OnHotSaleClickedListener {

    fun onClicked(hotSale: HotSale)
}