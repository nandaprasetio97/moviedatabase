package com.nandaprasetio.moviedatabase.core.misc.parallelloading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.compoundmodelvalue.ParallelLoadingCompoundModelValue
import kotlinx.coroutines.*

class ParallelLoadingManager {
    private val jobMap: MutableMap<String, Job> = mutableMapOf()
    private val parallelLoadingResultMutableList: MutableList<ParallelLoadingResult> = mutableListOf()

    // Parallel Mutable Live Data
    private val parallelMutableLiveData: MutableLiveData<ParallelLoadingResult> = ParallelMutableLiveData()
    val parallelLiveData: LiveData<ParallelLoadingResult> = parallelMutableLiveData

    // Parallel Loading Result Mutable Live Data
    private val parallelLoadingResultMutableLiveData: MutableLiveData<List<ParallelLoadingResult>> = ParallelMutableLiveData()
    val parallelLoadingResultLiveData: LiveData<List<ParallelLoadingResult>> = parallelLoadingResultMutableLiveData

    fun bindParallelLoadingResult() {
        parallelLoadingResultMutableLiveData.value = parallelLoadingResultMutableList
    }

    fun loadingParallel(
        key: String,
        baseModelValue: BaseModelValue,
        viewModelScope: CoroutineScope,
        coroutineDispatcher: CoroutineDispatcher
    ) {
        if (jobMap.containsKey(key)) {
            jobMap[key]?.cancel()
        }
        jobMap[key] = viewModelScope.launch(coroutineDispatcher) {
            if (baseModelValue is ParallelLoadingCompoundModelValue) {
                parallelLoadingResultMutableList.forEach {
                    if (it.parallelLoadingCompoundModelValueId == baseModelValue.id) {
                        return@launch
                    }
                }
                val parallelLoadingResult = ParallelLoadingResult(
                    parallelLoadingCompoundModelValueId = baseModelValue.id,
                    oldBaseModelValue = baseModelValue.oldItemModelValue,
                    newBaseModelValue = baseModelValue.onParallelLoading.invoke()
                )
                parallelLoadingResultMutableList.add(parallelLoadingResult)
                parallelMutableLiveData.postValue(parallelLoadingResult)
            }
        }
    }

    fun clearParallelLoadingResult() {
        parallelLoadingResultMutableList.clear()
    }
}