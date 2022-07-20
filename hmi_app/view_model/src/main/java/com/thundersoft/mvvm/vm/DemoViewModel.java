package com.thundersoft.mvvm.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.thundersoft.mvvm.repository.client.DemoUseCaseRepository;
import com.thundersoft.mvvm.vm.base.BaseViewModel;

public class DemoViewModel extends BaseViewModel<DemoUseCaseRepository> implements DemoUseCaseRepository.IDemoUseCaseRepository {

    private final MutableLiveData<String> mMutableLiveData = new MutableLiveData<>();


    public DemoViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void createRepository() {
        mRepository = new DemoUseCaseRepository(getApplication());
        mRepository.setDemoUseCaseRepository(this);
    }

    @Override
    public void onCallback(String message) {
        getMutableLiveData().postValue(message);
    }

    public MutableLiveData<String> getMutableLiveData() {
        return mMutableLiveData;
    }

    public void requestMessage(int type) {
        mRepository.requestMessage(type);
    }
}