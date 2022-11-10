package ru.malis.feature_main.internal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.malis.feature_main.R
import ru.malis.feature_main.databinding.ItemCategoryBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.malis.core_domain.models.Category

internal class CategoryListAdapter(
    private val onCategoryClickedListener: OnCategoryClickedListener
): RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        private val onCategoryClickedListener: OnCategoryClickedListener
    ): RecyclerView.ViewHolder(inflater.inflate(R.layout.item_category, parent, false)) {

        private val binding by viewBinding(ItemCategoryBinding::bind)

        fun bind(category: Category) {
            binding.apply {
                itemCategoryIv.setImageResource(category.image)
                itemCategoryName.text = category.title
            }

            itemView.setOnClickListener(null)
            itemView.setOnClickListener {
                onCategoryClickedListener.onClicked(category)
            }
        }
    }

    var categories = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryViewHolder(inflater, parent, onCategoryClickedListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int = categories.size
}

internal fun interface OnCategoryClickedListener {

    fun onClicked(category: Category)
}