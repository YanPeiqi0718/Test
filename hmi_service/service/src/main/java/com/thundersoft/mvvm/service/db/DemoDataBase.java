/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm.service.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.thundersoft.mvvm.service.dao.DemoDao;
import com.thundersoft.mvvm.service.entity.DemoEntity;

@Database( entities = {DemoEntity.class}, version = 1)
public abstract class DemoDataBase extends RoomDatabase {
    public abstract DemoDao demoDao();
}