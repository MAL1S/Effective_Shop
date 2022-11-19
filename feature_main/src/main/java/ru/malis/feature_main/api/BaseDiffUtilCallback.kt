package ru.malis.feature_main.api

import androidx.recyclerview.widget.DiffUtil
import ru.malis.core_domain.models.base.BaseIdClass

internal class BaseDiffUtilCallback(
    private var oldList: List<BaseIdClass>,
    private var newList: List<BaseIdClass>
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