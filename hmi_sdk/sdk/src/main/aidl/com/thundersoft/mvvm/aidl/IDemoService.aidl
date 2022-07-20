/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm.aidl;

import com.thundersoft.mvvm.aidl.IDemoCallback;

interface IDemoService {

    void registeCallback(IDemoCallback callback);

    void unregisteCallback(IDemoCallback callback);

    void requestMessage(int type);
}