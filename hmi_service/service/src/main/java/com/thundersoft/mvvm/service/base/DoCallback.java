/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm.service.base;

import android.os.IInterface;
import com.thundersoft.mvvm.utils.LogUtil;

public abstract class DoCallback<E extends IInterface> {

    private String mTag = "";

    protected BaseRemoteCallbackList<E> mRemoteCallbacks;

    protected DoCallback() {
        mTag = getLogTag();
        mRemoteCallbacks = new BaseRemoteCallbackList<E>();
    }

    protected abstract String getLogTag();

    /**
     * Add callback into RemoteCallbackList.
     *
     * @param callback user add callback
     * @return success or not
     */
    public boolean register(E callback) {
        return mRemoteCallbacks.register(callback);
    }

    /**
     * Remove callback from RemoteCallbackList.
     *
     * @param callback user add callback
     * @return success or not
     */
    public boolean unregister(E callback) {
        return mRemoteCallbacks.unregister(callback);
    }

    /**
     * Kill callback from RemoteCallbackList.
     */
    public void kill() {
        mRemoteCallbacks.kill();
        mRemoteCallbacks = null;
        mRemoteCallbacks = new BaseRemoteCallbackList<E>();
    }

    /**
     * Check callback is null or not to avoid when system kill callback object
     * may lead null pointer exception.
     *
     * @param index callback index
     */
    protected void checkCallbacksValid(int index) {
        if (mRemoteCallbacks == null) {
            return;
        }
        E callback = mRemoteCallbacks.getBroadcastItem(index);
        if (callback == null) {
            LogUtil.logE(mTag, "check callbacks is null!!");
            return;
        }
    }

    protected boolean isCallbackValid() {
        if (mRemoteCallbacks == null) {
            LogUtil.logE(mTag, "Remote callbacks is null!!");
            return false;
        }
        return true;
    }
}
