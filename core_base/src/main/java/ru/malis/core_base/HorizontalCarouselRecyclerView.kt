package ru.malis.core_base

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.pow


class HorizontalCarouselRecyclerView(
    context: Context,
    attrs: AttributeSet
) : RecyclerView(context, attrs) {

    fun <T : ViewHolder> initialize(newAdapter: Adapter<T>) {
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        newAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                post {
                    addOnScrollListener(object : OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            onScrollChanged()
                        }

                        override fun onScrollStateChanged(
                            recyclerView: RecyclerView,
                            newState: Int
                        ) {
                            super.onScrollStateChanged(recyclerView, newState)

                            if (newState == SCROLL_STATE_IDLE) {
                                val smoothScroller = CenterSmoothScroller(context)
                                smoothScroller.targetPosition = findViewToScrollToCenter()
                                layoutManager?.startSmoothScroll(
                                    smoothScroller
                                )
                            }
                        }
                    })
                }
            }
        })
        adapter = newAdapter
    }

    private fun findViewToScrollToCenter(): Int {
        val firstCompletelyVisibleItemPosition =
            (layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()

        if (firstCompletelyVisibleItemPosition != -1) {
            return firstCompletelyVisibleItemPosition
        }

        val first = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        val last = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

        val childFirst = getChildAt(0)
        val childLast = getChildAt(childCount-1)

        return if (childFirst.right > (right - childLast.left)) {
            first
        } else last
    }

    private fun onScrollChanged() {
        post {
            (0 until childCount).forEach { position ->
                val child = getChildAt(position)
                val childCenterX = (child.left + child.right) / 2
                var scaleValue = getGaussianScale(childCenterX, 1f, 1f, 150.toDouble()) - 0.1f
                if (scaleValue > 1f) scaleValue = 1f
                child.scaleX = scaleValue
                child.scaleY = scaleValue
            }
        }
    }

    private fun getGaussianScale(
        childCenterX: Int,
        minScaleOffest: Float,
        scaleFactor: Float,
        spreadFactor: Double
    ): Float {
        val recyclerCenterX = (left + right) / 2
        return (Math.E.pow(
            -(childCenterX - recyclerCenterX.toDouble()).pow(2.toDouble()) / (2 * spreadFactor.pow(2.toDouble()))
        ) * scaleFactor + minScaleOffest).toFloat()
    }
}