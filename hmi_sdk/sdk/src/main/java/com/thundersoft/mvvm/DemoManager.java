/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;

import androidx.annotation.NonNull;

import com.thundersoft.mvvm.aidl.IDemoCallback;
import com.thundersoft.mvvm.aidl.IDemoService;
import com.thundersoft.mvvm.base.BaseManager;

public class DemoManager extends BaseManager {

    public static String TAG = "MvvmDemoManager";
    private static final String SERVICE_PK_NAME = "com.thundersoft.mvvm.service";
    private static final String SERVICE_ACTION = "com.thundersoft.mvvm.demo.service";

    private static DemoManager sInstance;
    private IDemoService mDemoService;
    private static boolean isConnection = false;
    private static IConnectionCallBack mCallback;

    private static final int MASSAGE_CONNECT_SUCCESS = 0;
    private static final int MASSAGE_CONNECT_FAILD = 0;
    private static final int TIME_MASSAGE_DELAYED = 500;
    protected static Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (mCallback != null) {
                mCallback.onSuccess();
            }
        }
    };

    private DemoManager(Context context) {
        super(context);
    }

    public static synchronized DemoManager init(Context context, @NonNull IConnectionCallBack callback) {
        mCallback = callback;
        if (null == sInstance) {
            synchronized (DemoManager.class){
                if (null == sInstance) {
                    sInstance = new DemoManager(context);
                }
            }
        }
        if (isConnection) {
            mHandler.sendEmptyMessageDelayed(MASSAGE_CONNECT_SUCCESS, TIME_MASSAGE_DELAYED);
        }
        return sInstance;
    }

    @Override
    protected String getPackageName() {
        return SERVICE_PK_NAME;
    }

    @Override
    protected String getAction() {
        return SERVICE_ACTION;
    }

    @Override
    protected void setBinder(IBinder binder) {
        synchronized (DemoManager.class) {
            if (binder != null) {
                mDemoService = IDemoService.Stub.asInterface(binder);
                isConnection = true;
                mCallback.onSuccess();
            } else {
                mDemoService = null;
                isConnection = false;
                mCallback.onFailure();
            }
        }
    }

    @Override
    protected void destroy() {

    }

    /**
     * registeCallback.
     *
     * @param callback IDemoCallback.
     */
    public void registeCallback(IDemoCallback callback) {
        if (mDemoService != null) {
            try {
                mDemoService.registeCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * unregisteCallback.
     *
     * @param callback IDemoCallback.
     */
    public void unregisteCallback(IDemoCallback callback) {
        if (mDemoService != null) {
            try {
                mDemoService.unregisteCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Demo function.
     *
     * @param type Type of callback message.
     */
    public void requestMessage(int type) {
        if (mDemoService != null) {
            try {
                mDemoService.requestMessage(type);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}