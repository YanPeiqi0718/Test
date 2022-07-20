package com.thundersoft.mvvm.vm.manager;

import androidx.lifecycle.MutableLiveData;

public class SingleLiveDataManager {

    private static SingleLiveDataManager INSTANCE;

    private MutableLiveData<Integer> mShowFragmentLiveData = new MutableLiveData<>();

    public static synchronized SingleLiveDataManager getInstance() {
        if (null == INSTANCE) {
            synchronized (SingleLiveDataManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new SingleLiveDataManager();
                }
            }
        }
        return INSTANCE;
    }

    public MutableLiveData<Integer> getShowFragmentLiveData() {
        return mShowFragmentLiveData;
    }
}