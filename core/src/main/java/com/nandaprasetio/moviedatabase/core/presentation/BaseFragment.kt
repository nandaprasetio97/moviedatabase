package com.nandaprasetio.moviedatabase.core.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T: ViewBinding>: Fragment() {
    protected var viewBinding: T? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = onGetViewBinding(inflater, container)
        return viewBinding?.root
    }

    abstract fun onGetViewBinding(inflater: LayoutInflater, container: ViewGroup?): T

    override fun onDestroyView() {
        super.onDestroyView()
        onDestroyingViewBeforeViewBindingIsNull()
        viewBinding = null
    }

    protected open fun onDestroyingViewBeforeViewBindingIsNull() {}
}