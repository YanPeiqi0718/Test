/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm.service.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;

public class BaseService <T extends BaseServiceRepository> extends Service implements LifecycleOwner {
    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    private MutableLiveData<String> mMutableLiveData = new MutableLiveData<>();

    protected T mRepository;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mLifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);

        mMutableLiveData.observe(this, s -> {

        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mLifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mLifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        mLifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
        super.onDestroy();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
