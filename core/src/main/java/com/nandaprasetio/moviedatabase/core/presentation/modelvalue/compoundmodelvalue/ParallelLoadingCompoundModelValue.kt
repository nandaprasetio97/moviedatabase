package com.nandaprasetio.moviedatabase.core.presentation.modelvalue.compoundmodelvalue

import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue

data class ParallelLoadingCompoundModelValue(
    override val id: String,
    val oldItemModelValue: List<BaseModelValue>,
    val onParallelLoading: suspend () -> List<BaseModelValue>,
): BaseCompoundModelValue()