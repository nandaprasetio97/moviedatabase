package com.nandaprasetio.moviedatabase.presentation.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyControllerAdapter
import com.airbnb.epoxy.EpoxyItemSpacingDecorator
import com.airbnb.epoxy.EpoxyModel
import com.nandaprasetio.moviedatabase.core.presentation.epoxymodel.HasLeftRightPaddingEpoxyModel

class ModifiedEpoxyItemSpacingDecorator: EpoxyItemSpacingDecorator() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.left = 0
        outRect.right = 0
        outRect.top = 0
        outRect.bottom = 0

        parent.layoutManager?.also {
            val horizontallyScroll = it.canScrollHorizontally()
            val verticallyScroll = it.canScrollVertically()
            if (it is GridLayoutManager) {
                val itemCount = parent.adapter?.itemCount ?: 0
                val position = parent.getChildAdapterPosition(view)
                val spanSizeLookup = it.spanSizeLookup
                val spanSize = spanSizeLookup.getSpanSize(position)
                val spanCount = it.spanCount
                val spanIndex: Int = spanSizeLookup.getSpanIndex(position, spanCount)
                val isFirstItemInRow = spanIndex == 0
                val fillsLastSpan = spanIndex + spanSize == spanCount

                //println("Position: $position, Span count: $spanCount, Span size: $spanSize, Span index: $spanIndex")
                val epoxyControllerAdapter: EpoxyControllerAdapter = parent.adapter as EpoxyControllerAdapter
                if (verticallyScroll) {
                    if (position > -1) {
                        if (allowItemToUseLeftRightMargin(epoxyControllerAdapter.getModelAtPosition(position))) {
                            if (isFirstItemInRow) outRect.left = pxBetweenItems
                            if (fillsLastSpan) outRect.right = pxBetweenItems
                        }
                    }
                }
            }
        }
    }

    private fun allowItemToUseLeftRightMargin(epoxyModel: EpoxyModel<*>): Boolean {
        return epoxyModel is HasLeftRightPaddingEpoxyModel
    }
}