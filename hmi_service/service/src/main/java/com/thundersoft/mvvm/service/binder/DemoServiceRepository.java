/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm.service.binder;

import android.os.RemoteException;

import com.thundersoft.mvvm.aidl.IDemoCallback;
import com.thundersoft.mvvm.aidl.IDemoService;
import com.thundersoft.mvvm.utils.LogUtil;
import com.thundersoft.mvvm.service.base.BaseServiceRepository;
import com.thundersoft.mvvm.service.callback.DemoCallBack;


public class DemoServiceRepository extends BaseServiceRepository {

    private static final String TAG = DemoServiceStub.class.getSimpleName();

    private DemoCallBack demoCallBack;

    private DemoServiceStub demoServiceStub;

    public DemoServiceStub getDemoServiceStub() {
        demoServiceStub = new DemoServiceStub();
        return demoServiceStub;
    }

    public class DemoServiceStub extends IDemoService.Stub {


        public DemoServiceStub() {
            demoCallBack = new DemoCallBack();
        }

        /**
         * 注册.
         * @param callback callback
         * @throws RemoteException RemoteException
         */
        @Override
        public void registeCallback(IDemoCallback callback) throws RemoteException {
            LogUtil.logD(TAG,"registeCallback");
            if (demoCallBack != null && callback != null) {
                demoCallBack.register(callback);
            }
        }

        /**
         * 解注册.
         * @param callback callback
         * @throws RemoteException RemoteException
         */
        @Override
        public void unregisteCallback(IDemoCallback callback) throws RemoteException {
            LogUtil.logD(TAG,"unregisteCallback");
            if (demoCallBack != null && callback != null) {
                demoCallBack.unregister(callback);
            }
        }
        /**
         * 请求、回调.
         * @param type type
         * @throws RemoteException RemoteException
         */
        @Override
        public void requestMessage(int type) throws RemoteException {
            LogUtil.logD(TAG,"demoChangeType:" + type);
            if (type == 1) {
                if (demoCallBack != null) {
                    demoCallBack.onCallback("Demo");
                }
            }
        }

    }

}
