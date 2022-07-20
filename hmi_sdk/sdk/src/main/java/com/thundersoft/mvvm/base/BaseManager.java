/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */
package com.thundersoft.mvvm.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public abstract class BaseManager {

    private static final String TAG = "BaseManager";
    protected Context mContext;
    protected ServiceConnection mServiceConnection;
    protected boolean mBinderResult;
    protected boolean mServiceBound;
    private RetryHandler mRetryHandler;
    private HandlerThread mRetryHandlerThread;
    private static final String RETRY = "retry_thread";
    private static final int MSG_RETRY_BIND_SERVICE = 0;
    private static final int TIME_RETRY_BIND_SERVICE = 1000;

    protected abstract String getPackageName();
    protected abstract String getAction();
    protected abstract void setBinder(IBinder binder);
    protected abstract void destroy();

    protected BaseManager(Context context) {
        mContext = context.getApplicationContext();
        if (mRetryHandlerThread == null) {
            mRetryHandlerThread = new HandlerThread(RETRY);
            mRetryHandlerThread.start();
        }
        if (mRetryHandler == null) {
            mRetryHandler = new RetryHandler(mRetryHandlerThread.getLooper());
        }
        if (!mBinderResult) {
            bindService();
        }
    }

    /**
     * unbind service and release callback.
     */
    public void release() {
        unBindService();
        mBinderResult = false;
        mServiceBound = false;
        destroy();
    }

    protected void bindService() {
        Intent intent = new Intent(getAction()).setPackage(getPackageName());
        if (mContext != null) {
            mBinderResult = mContext.bindService(intent, getConnection(), Context.BIND_AUTO_CREATE);
            Log.i(TAG, "BindService mBinderResult:" + mBinderResult);
            if (!mBinderResult) {
                if (mRetryHandler.hasMessages(MSG_RETRY_BIND_SERVICE)) {
                    mRetryHandler.removeMessages(MSG_RETRY_BIND_SERVICE);
                }
                mRetryHandler.sendEmptyMessageDelayed(MSG_RETRY_BIND_SERVICE,
                        TIME_RETRY_BIND_SERVICE);
            }
        }
    }

    /**
     * Re-bind Settings Service.
     *
     */
    public void reBindService() {
        Log.i(TAG, "reBindService:" + mServiceBound);
        if (!mServiceBound) {
            bindService();
        }
    }

    protected void unBindService() {
        mContext.unbindService(mServiceConnection);
    }

    private ServiceConnection getConnection() {
        if (mServiceConnection == null) {
            mServiceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    if (iBinder == null) {
                        Log.d(TAG, "onServiceConnected: iBinder is null!");
                        return;
                    } else {
                        Log.d(TAG, "onServiceConnected: Binder is success!");
                        setBinder(iBinder);
                        mServiceBound = true;
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    Log.d(TAG, "onServiceDisconnected");
                    setBinder(null);
                    mBinderResult = false;
                    mServiceBound = false;
                    reBindService();
                }
            };
        }
        return mServiceConnection;
    }

    private class RetryHandler extends Handler {
        public RetryHandler() {}

        public RetryHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_RETRY_BIND_SERVICE:
                    reBindService();
                    break;
                default:
                    break;
            }
        }
    }
}

