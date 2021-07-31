package com.nandaprasetio.moviedatabase.core.misc.parallelloading

import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue

class ParallelLoadingResult(
    val parallelLoadingCompoundModelValueId: String,
    val oldBaseModelValue: List<BaseModelValue>,
    val newBaseModelValue: List<BaseModelValue>
)