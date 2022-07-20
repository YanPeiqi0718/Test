/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm.service;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.thundersoft.mvvm.service.binder.DemoServiceRepository;
import com.thundersoft.mvvm.service.base.BaseService;


public class DemoService extends BaseService<DemoServiceRepository> {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mRepository.getDemoServiceStub();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mRepository = new DemoServiceRepository();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
