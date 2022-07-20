/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm.service.base;

import android.os.Build;
import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import androidx.annotation.RequiresApi;

public class BaseRemoteCallbackList<E extends IInterface> extends RemoteCallbackList<E> {

    private OnCallbackListListener mListener;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCallbackDied(E callback) {
        super.onCallbackDied(callback);
        if (mListener != null) {
            try {
                mListener.onCallbackDied(getRegisteredCallbackCount());
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void onCallbackDied(E callback, Object cookie) {
        onCallbackDied(callback);
    }

    public void setOnCallbackListListener(OnCallbackListListener listener) {
        mListener = listener;
    }

    /**
     * CallbackList listener interface.
     */
    public interface OnCallbackListListener {
        /**
         * OnCallbackDied by listener.
         *
         * @param count callback list count
         * @throws RemoteException remote exception
         */
        void onCallbackDied(int count) throws RemoteException;
    }
}
