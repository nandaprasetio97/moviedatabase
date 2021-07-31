package com.nandaprasetio.moviedatabase.core.misc.parallelloading;

import android.annotation.SuppressLint;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.MutableLiveData;

public class ParallelMutableLiveData<T> extends MutableLiveData<T> {
    @SuppressWarnings("WeakerAccess")
    final Object mDataLock = new Object();

    public ParallelMutableLiveData(T value) {
        super(value);
    }

    public ParallelMutableLiveData() {
        super();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void postValue(T value) {
        synchronized (mDataLock) {
            ArchTaskExecutor.getInstance().postToMainThread(new ParallelMutableLiveDataRunnable(value));
        }
    }

    @Override
    public void setValue(T value) {
        super.setValue(value);
    }

    class ParallelMutableLiveDataRunnable implements Runnable {
        private volatile Object pendingData;

        ParallelMutableLiveDataRunnable(Object pendingData) {
            this.pendingData = pendingData;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void run() {
            synchronized (mDataLock) {
                setValue((T) pendingData);
            }
        }
    }
}