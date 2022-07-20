/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm.repository.client;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import com.thundersoft.mvvm.DemoManager;
import com.thundersoft.mvvm.IConnectionCallBack;
import com.thundersoft.mvvm.aidl.IDemoCallback;
import com.thundersoft.mvvm.repository.utils.LogUtil;
import com.thundersoft.mvvm.repository.client.base.BaseUseCaseRepository;

public class DemoUseCaseRepository extends BaseUseCaseRepository {

    private static final String TAG = DemoUseCaseRepository.class.getSimpleName();

    private DemoModelStub demoModelStub;

    private IDemoUseCaseRepository demoUseCaseRepository;

    private DemoManager mDemoManger;
    private Context mContext;

    public DemoUseCaseRepository(Context context) {
        demoModelStub = new DemoModelStub();
        mContext = context;
        mDemoManger = DemoManager.init(mContext, new IConnectionCallBack() {
            @Override
            public void onSuccess() {
                Log.d(DemoManager.TAG, "DemoModel Connect onSuccess!");
                if (mDemoManger != null) {
                    mDemoManger.registeCallback(demoModelStub);
                }
            }

            @Override
            public void onFailure() {
                Log.d(DemoManager.TAG, "DemoModel Connect onFailure!");
                if (mDemoManger != null) {
                    mDemoManger.unregisteCallback(demoModelStub);
                }
            }
        });
    }

    public void requestMessage(int type) {
        if (mDemoManger != null) {
            mDemoManger.requestMessage(type);
        }
    }

    private class DemoModelStub extends IDemoCallback.Stub {

        @Override
        public void onCallback(String message) throws RemoteException {
            LogUtil.logD(TAG,"message:" + message);
            if (demoUseCaseRepository != null) {
                demoUseCaseRepository.onCallback(message);
            }
        }
    }

    public void setDemoUseCaseRepository(IDemoUseCaseRepository iDemoUseCaseRepository) {
        demoUseCaseRepository = iDemoUseCaseRepository;
    }

    public interface IDemoUseCaseRepository {
        void onCallback(String message);
    }

}
