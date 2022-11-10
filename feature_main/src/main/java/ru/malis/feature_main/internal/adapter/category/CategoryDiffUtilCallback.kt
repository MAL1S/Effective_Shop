package ru.malis.feature_main.internal.adapter.category

import androidx.recyclerview.widget.DiffUtil
import ru.malis.core_domain.models.Category

internal class CategoryDiffUtilCallback(
    private var oldList: List<Category>,
    private var newList: List<Category>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}