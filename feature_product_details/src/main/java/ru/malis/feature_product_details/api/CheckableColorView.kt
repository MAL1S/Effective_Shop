package ru.malis.feature_product_details.api

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.setPadding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import ru.malis.core_util.dimens.dp
import ru.malis.feature_product_details.R

private const val COLOR_CARD_VIEW = "COLOR_CARD_VIEW"

class CheckableColorView : FlexboxLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    )

    private var container: FlexboxLayout

    private var colors: List<Int> = mutableListOf()

    init {
        inflate(context, R.layout.flexbox_base, this)

        container = findViewById(R.id.flexbox_base_layout)

        container.flexDirection = FlexDirection.ROW
        container.flexWrap = FlexWrap.WRAP
    }

    fun setColors(colors: List<String>) {
        this.colors = colors.map {
            Color.parseColor(it)
        }

        container.removeAllViews()
        for ((index, color) in colors.withIndex()) {
            addColorView(index, Color.parseColor(color))
        }

        updateChosenViews(0)
    }

    private fun addColorView(index: Int, color: Int) {
        val colorCardView = CardView(context)
        val chosenImageView = ImageView(context)
        chosenImageView.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                ru.malis.core_style.R.drawable.ic_chosen
            )
        )
        chosenImageView.setPadding(12.dp(context))
        chosenImageView.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        chosenImageView.tag="chosenImageView"
        colorCardView.addView(chosenImageView)
        colorCardView.preventCornerOverlap = true
        colorCardView.radius = 100f
        val size = 40.dp(context)
        val layoutParams = LayoutParams(size, size)
        layoutParams.marginEnd = 18.dp(context)
        layoutParams.bottomMargin = 18.dp(context)
        colorCardView.layoutParams = layoutParams
        colorCardView.setCardBackgroundColor(color)

        colorCardView.setOnClickListener {
            updateChosenViews(it.tag.toString().substring(COLOR_CARD_VIEW.length).toInt())
        }

        colorCardView.tag = "$COLOR_CARD_VIEW$index"
        container.addView(colorCardView)
    }

    private fun updateChosenViews(chosenId: Int) {
        for (childPosition in 0 until container.childCount) {
            setChosen(childPosition, chosenId == childPosition)
        }
    }

    private fun setChosen(position: Int, isChosen: Boolean) {
        val child = container.getChildAt(position)
        val imageView = child.findViewWithTag<ImageView>("chosenImageView") ?: return
        imageView.visibility = if (isChosen) View.VISIBLE else View.INVISIBLE
    }
}