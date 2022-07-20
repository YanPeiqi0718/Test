package com.thundersoft.mvvm.vm.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.thundersoft.mvvm.repository.client.base.BaseUseCaseRepository;
import com.thundersoft.mvvm.vm.manager.SingleLiveDataManager;

public abstract class BaseViewModel <T extends BaseUseCaseRepository> extends AndroidViewModel {

    public SingleLiveDataManager mSingleLiveDataManager;

    protected T mRepository;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        mSingleLiveDataManager = SingleLiveDataManager.getInstance();
        createRepository();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    protected abstract void createRepository();

    public void changedFragment(int type) {
        mSingleLiveDataManager.getShowFragmentLiveData().postValue(type);
    }
}
