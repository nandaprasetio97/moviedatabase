package com.nandaprasetio.moviedatabase.presentation.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Px
import com.airbnb.epoxy.EpoxyItemSpacingDecorator
import com.airbnb.epoxy.EpoxyRecyclerView
import com.nandaprasetio.moviedatabase.presentation.itemdecoration.ModifiedEpoxyItemSpacingDecorator

class ModifiedEpoxyRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): EpoxyRecyclerView(context, attrs, defStyleAttr) {
    private val modifiedSpacingDecorator: EpoxyItemSpacingDecorator? = ModifiedEpoxyItemSpacingDecorator()

    override fun setItemSpacingPx(@Px spacingPx: Int) {
        // Because it's also called in base class constructor, therefore check if modifiedSpacingDecorator is null or not.
        // Remembering if modifiedSpacingDecorator is initialized after base class init method is called.
        if (modifiedSpacingDecorator == null) {
            super.setItemSpacingPx(spacingPx)
            return
        }

        if (itemDecorationCount > 0) {
            removeItemDecorationAt(0)
        }
        modifiedSpacingDecorator.pxBetweenItems = spacingPx

        if (spacingPx > 0) {
            addItemDecoration(modifiedSpacingDecorator)
        }
    }
}