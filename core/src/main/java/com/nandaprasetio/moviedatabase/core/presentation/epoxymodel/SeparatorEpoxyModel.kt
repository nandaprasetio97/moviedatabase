package com.nandaprasetio.moviedatabase.core.presentation.epoxymodel

import android.util.TypedValue
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.moviedatabase.core.R
import com.nandaprasetio.moviedatabase.core.presentation.epoxyholder.SeparatorEpoxyHolder
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.SeparatorItemModelValue

@EpoxyModelClass
abstract class SeparatorEpoxyModel: EpoxyModelWithHolder<SeparatorEpoxyHolder>(), HasLeftRightPaddingEpoxyModel {
    @EpoxyAttribute
    var separatorItemModelValue: SeparatorItemModelValue? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_separation
    }

    override fun bind(holder: SeparatorEpoxyHolder) {
        super.bind(holder)
        separatorItemModelValue?.also { value ->
            holder.spacingView.also {
                if (it.layoutParams is ViewGroup.LayoutParams) {
                    val displayMetrics = it.resources.displayMetrics
                    val layoutParams = it.layoutParams as ViewGroup.LayoutParams
                    layoutParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.spacingDp, displayMetrics).toInt()
                }
            }
        }
    }
}