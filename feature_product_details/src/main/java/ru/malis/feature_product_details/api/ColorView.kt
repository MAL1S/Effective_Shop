package ru.malis.feature_product_details.api

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import ru.malis.feature_product_details.R

class ColorView : CardView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    )

    private var container: CardView
    private var chosenImageView: ImageView

    init {
        inflate(context, R.layout.color_view_base, this)

        container = findViewById(R.id.color_view_layout)
        chosenImageView = findViewById(R.id.color_view_iv_chosen)
    }

    fun setColor(colorResource: Int) {
        container.setBackgroundColor(colorResource)
    }

    fun setColorChosen(isSelected: Boolean) {
        chosenImageView.visibility = if (isSelected) View.VISIBLE else View.INVISIBLE
    }
}