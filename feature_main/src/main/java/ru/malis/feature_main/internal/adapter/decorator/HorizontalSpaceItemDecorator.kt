package ru.malis.feature_main.internal.adapter.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.malis.core_style.R as style

class HorizontalSpaceItemDecorator: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val horizontalDp = parent.context.resources.getDimension(style.dimen.listHorizontalMargin).toInt()

        if (parent.adapter?.itemCount != null && parent.getChildAdapterPosition(view) == 0) {
            outRect.left = horizontalDp
        }
        outRect.right = horizontalDp
    }
}