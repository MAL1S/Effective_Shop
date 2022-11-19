package ru.malis.feature_product_details.api

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import ru.malis.core_util.dimens.dp
import ru.malis.feature_product_details.R

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
        for (color in colors) {
            addColorView(Color.parseColor(color))
        }
    }

    private fun addColorView(color: Int) {
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
            Log.d("testing", "$focusedChild")
        }

        container.addView(colorCardView)
    }
}