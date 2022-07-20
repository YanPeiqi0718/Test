/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm.service.callback;

import android.os.RemoteException;

import com.thundersoft.mvvm.aidl.IDemoCallback;
import com.thundersoft.mvvm.utils.LogUtil;
import com.thundersoft.mvvm.service.base.DoCallback;


public class DemoCallBack extends DoCallback<IDemoCallback> {

    private static final String TAG = DemoCallBack.class.getSimpleName();

    @Override
    protected String getLogTag() {
        return TAG;
    }
    /**
     * call back state.
     *
     * @param state demo state.
     */
    public void onCallback(String state) {
        LogUtil.logD(TAG, "onCallback state = " + state);
        int count = mRemoteCallbacks.beginBroadcast();
        for (int i = 0; i < count; i++) {
            try {
                mRemoteCallbacks.getBroadcastItem(i).onCallback(state);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        mRemoteCallbacks.finishBroadcast();
    }
}
