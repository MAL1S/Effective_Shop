package ru.malis.feature_product_details.api

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import ru.malis.core_util.dimens.dp
import ru.malis.feature_product_details.R

private const val TEXT_CARD_VIEW = "COLOR_CARD_VIEW"

class CheckableTextView : FlexboxLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        val attrs = context.obtainStyledAttributes(
            attributeSet,
            ru.malis.core_style.R.styleable.CheckableTextView,
            0,
            0
        )

        selectedColor = attrs.getColor(
            ru.malis.core_style.R.styleable.CheckableTextView_selectedColor,
            Color.BLUE
        )

        attrs.recycle()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        val attrs = context.obtainStyledAttributes(
            attributeSet,
            ru.malis.core_style.R.styleable.CheckableTextView,
            defStyle,
            0
        )

        selectedColor = attrs.getColor(
            ru.malis.core_style.R.styleable.CheckableTextView_selectedColor,
            Color.BLUE
        )

        attrs.recycle()
    }

    private var container: FlexboxLayout

    private var labels: List<String> = mutableListOf()

    private var selectedColor: Int = Color.BLUE

    init {
        inflate(context, R.layout.flexbox_base, this)

        container = findViewById(R.id.flexbox_base_layout)

        container.flexDirection = FlexDirection.ROW
        container.flexWrap = FlexWrap.WRAP
    }

    fun setLabels(labels: List<String>) {
        this.labels = labels

        container.removeAllViews()
        for ((index, label) in labels.withIndex()) {
            addTextView(index, label)
        }

        updateChosenViews(0)
    }

    private fun addTextView(index: Int, label: String) {
        val textCardView = CardView(context)
        val textView = TextView(context)
        textView.text = label
        textView.setTextColor(
            ContextCompat.getColor(
                context,
                ru.malis.core_style.R.color.gray_light_3
            )
        )
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        textView.typeface =
            ResourcesCompat.getFont(context, ru.malis.core_style.R.font.mark_pro_bold)

        textView.setPadding(16.dp(context), 8.dp(context), 16.dp(context), 8.dp(context))
        textView.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        textView.tag = "textView"
        textCardView.addView(textView)
        textCardView.preventCornerOverlap = true
        textCardView.radius = 10.dp(context).toFloat()
        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.marginEnd = 14.dp(context)
        layoutParams.bottomMargin = 14.dp(context)
        textCardView.layoutParams = layoutParams
        textCardView.setCardBackgroundColor(Color.WHITE)

        textCardView.setOnClickListener {
            updateChosenViews(it.tag.toString().substring(TEXT_CARD_VIEW.length).toInt())
        }

        textCardView.tag = "$TEXT_CARD_VIEW$index"
        container.addView(textCardView)
    }

    private fun updateChosenViews(chosenId: Int) {
        for (childPosition in 0 until container.childCount) {
            setChosen(childPosition, chosenId == childPosition)
        }
    }

    private fun setChosen(position: Int, isChosen: Boolean) {
        val child = container.getChildAt(position)
        (child as CardView).apply {
            setCardBackgroundColor(
                if (isChosen) selectedColor
                else Color.WHITE
            )
        }
        val textView = child.findViewWithTag<TextView>("textView") ?: return
        textView.setTextColor(
            if (isChosen) Color.WHITE
            else Color.GRAY
        )
    }
}